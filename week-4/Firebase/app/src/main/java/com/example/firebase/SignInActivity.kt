package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import java.util.Arrays

class SignInActivity : AppCompatActivity() {

    private val TAG = "SignInActivity"
    private lateinit var auth: FirebaseAuth

    // 1. Khai báo ActivityResultLauncher để xử lý kết quả FirebaseUI
    private val signInLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { result ->
        // Gọi hàm xử lý kết quả đăng nhập
        onSignInResult(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Lưu ý: Không cần đặt layout cho màn hình này, vì FirebaseUI sẽ vẽ UI.

        auth = FirebaseAuth.getInstance()

        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (auth.currentUser != null) {
            // Nếu đã đăng nhập, chuyển hướng ngay lập tức đến MainActivity
            navigateToMainScreen()
        } else {
            // Nếu chưa, bắt đầu luồng đăng nhập
            createSignInIntent()
        }
    }

    // 2. Tạo và Khởi chạy Intent đăng nhập của FirebaseUI
    private fun createSignInIntent() {
        // Chọn các nhà cung cấp đăng nhập (Providers)
        val providers = Arrays.asList(
            AuthUI.IdpConfig.EmailBuilder().build(), // Email/Password
            AuthUI.IdpConfig.GoogleBuilder().build()  // Google Sign-In
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            // .setTheme(R.style.Theme_YourApp) // Tùy chọn: thiết lập giao diện
            .setIsSmartLockEnabled(false) // Tùy chọn: Vô hiệu hóa Smart Lock
            .build()

        signInLauncher.launch(signInIntent)
    }

    // 3. Xử lý kết quả trả về từ FirebaseUI
    private fun onSignInResult(result: com.firebase.ui.auth.FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            // Đăng nhập thành công!
            val user = auth.currentUser
            saveUserInfo(user)
            navigateToMainScreen()

        } else {
            // Đăng nhập thất bại
            val response = result.idpResponse
            if (response == null) {
                // Người dùng nhấn nút Back
                Toast.makeText(this, "Đăng nhập bị hủy", Toast.LENGTH_SHORT).show()
                finish() // Đóng Activity
            } else {
                // Xử lý lỗi
                Log.e(TAG, "Lỗi đăng nhập: ", response.error)
                Toast.makeText(this, "Đăng nhập thất bại: ${response.error?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    // 4. Lấy và Lưu thông tin người dùng (Tên và Email)
    private fun saveUserInfo(user: FirebaseUser?) {
        user?.let {
            // **Thông tin bạn cần:**
            val displayName = it.displayName
            val email = it.email
            val uid = it.uid

            // **TODO:** Bạn có thể lưu thông tin này vào SharedPreferences, Room Database,
            // hoặc gửi lên Firestore/Realtime Database nếu cần.

            Log.i(TAG, "Đã đăng nhập - Tên: $displayName, Email: $email, UID: $uid")

            // Ví dụ: Đơn giản chỉ là Toast thông báo
            Toast.makeText(this, "Chào mừng $displayName!\nEmail: $email", Toast.LENGTH_LONG).show()
        }
    }

    // Chuyển hướng đến MainActivity
    private fun navigateToMainScreen() {
        // Thay thế MainActivity::class.java bằng tên Activity chính của bạn nếu khác
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Đóng SignInActivity để người dùng không quay lại màn hình đăng nhập
    }
}