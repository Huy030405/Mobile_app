package com.example.api.ui.screens.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

// Đây là Composable cho màn hình rỗng (Empty View)
// Giống như hình "List Empty" trong slide của bạn
@Composable
fun EmptyView() {
    Column(
        modifier = Modifier.fillMaxSize(), // Chiếm toàn bộ màn hình
        verticalArrangement = Arrangement.Center, // Căn giữa theo chiều dọc
        horizontalAlignment = Alignment.CenterHorizontally // Căn giữa theo chiều ngang
    ) {
        // Bạn có thể thêm một Icon ở đây để giống slide
        // Icon(imageVector = Icons.Default.YourIcon, contentDescription = null)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "No Tasks Yet!",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Stay productive—add something to do!",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}