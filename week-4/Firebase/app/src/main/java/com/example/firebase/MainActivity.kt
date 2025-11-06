package com.example.firebase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    // Khai báo các biến cần thiết
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1. Khởi tạo Firebase Auth
        auth = Firebase.auth

        // Kiểm tra xem người dùng đã đăng nhập chưa. Nếu có, chuyển hướng ngay.
        if (auth.currentUser != null) {
            navigateToProfile()
            return
        }

        // 2. Cấu hình Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            // Lấy ID Token, RẤT QUAN TRỌNG để xác thực với Firebase
            .requestIdToken(getString(R.string.default_web_client_id)) // Lấy từ strings.xml (hoặc file cấu hình khác)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // 3. Xử lý sự kiện khi nhấn nút Google Sign-In
        findViewById<SignInButton>(R.id.btn_sign_in_google).setOnClickListener {
            signInGoogle()
        }
    }

    // Hàm bắt đầu quá trình đăng nhập Google
    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    // Sử dụng Activity Result API (Cách hiện đại hơn onActivityResult)
    private val googleSignInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleGoogleSignInResult(task)
            } else {
                // Thông báo lỗi cho người dùng
                Toast.makeText(this, "Đăng nhập Google bị hủy hoặc thất bại.", Toast.LENGTH_SHORT).show()
            }
        }

    // Hàm xử lý kết quả trả về từ Google Sign-In
    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)!!
            Log.d("Login", "Google Sign In thành công. ID Token: ${account.idToken}")
            // 4. Xác thực Firebase bằng Google ID Token
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            // Google Sign In thất bại (Lỗi kết nối, cấu hình...)
            Log.w("Login", "Google sign in failed", e)
            Toast.makeText(this, "Lỗi Google Sign In: ${e.statusCode} - ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    // Hàm chính xác thực với Firebase
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Đăng nhập thành công!
                    Log.d("Login", "Đăng nhập Firebase thành công")
                    // THÔNG BÁO KẾT QUẢ
                    Toast.makeText(this, "Đăng nhập thành công! ✅", Toast.LENGTH_SHORT).show()
                    // 5. Điều hướng
                    navigateToProfile()
                } else {
                    // Đăng nhập Firebase thất bại (Lỗi từ Firebase)
                    Log.w("Login", "Đăng nhập Firebase thất bại", task.exception)
                    // THÔNG BÁO KẾT QUẢ
                    Toast.makeText(this, "Xác thực Firebase thất bại: ${task.exception?.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
    }

    // Hàm điều hướng tới màn hình Profile
    private fun navigateToProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        // Kết thúc LoginActivity để không thể dùng nút Back quay lại đây
        finish()
    }
}