package com.example.api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.api.ui.navigation.AppNavigation
import com.example.api.ui.theme.APITheme // <-- Tên này có thể khác, Sửa lại theo tên Theme.kt của bạn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Áp dụng Theme (trong ui.theme)
            APITheme { // <-- Sửa tên "ApiTheme" nếu app của bạn tên khác
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // *** ĐÂY LÀ ĐIỂM QUAN TRỌNG NHẤT ***
                    // Gọi "bản đồ" (NavHost) mà chúng ta đã tạo
                    AppNavigation()
                }
            }
        }
    }
}