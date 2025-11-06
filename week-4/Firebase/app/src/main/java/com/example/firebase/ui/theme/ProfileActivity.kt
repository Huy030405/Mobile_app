package com.example.firebase.ui.theme

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
// Thêm thư viện Glide/Picasso để load ảnh (Nếu chưa có, bạn cần thêm dependency)
// Ví dụ: import com.bumptech.glide.Glide

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = Firebase.auth
        val user = auth.currentUser

        // Nếu vì lý do nào đó mà user null (chưa đăng nhập), bạn nên xử lý lỗi ở đây
        if (user == null) {
            // Quay lại màn hình Login (tùy chọn)
            finish()
            return
        }

        // Lấy các thành phần UI
        val tvName = findViewById<TextView>(R.id.tv_user_name)
        val tvEmail = findViewById<TextView>(R.id.tv_user_email)
        val imgAvatar = findViewById<ImageView>(R.id.img_profile_avatar)
        val btnBack = findViewById<Button>(R.id.btn_back)

        // 1. Hiển thị thông tin
        tvName.text = "Tên: ${user.displayName ?: "Không có tên"}"
        tvEmail.text = "Email: ${user.email ?: "Không có email"}"

        // 2. Load ảnh đại diện
        if (user.photoUrl != null) {
            // *** LƯU Ý: Để load ảnh từ URL, bạn CẦN thư viện bên ngoài như Glide, Picasso, hoặc Coil. ***
            // Ví dụ sử dụng Glide (nếu bạn đã thêm dependency):
            // Glide.with(this).load(user.photoUrl).into(imgAvatar)
            // Nếu không dùng thư viện, bạn chỉ thấy ảnh placeholder.
        }

        // 3. Xử lý nút Back
        btnBack.setOnClickListener {
            // Quay lại màn hình trước đó (hoặc Đăng xuất nếu bạn muốn)
            finish()
        }
    }
}