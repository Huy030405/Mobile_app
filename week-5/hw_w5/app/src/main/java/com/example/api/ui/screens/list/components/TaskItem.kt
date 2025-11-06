package com.example.api.ui.screens.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.api.data.model.Task

// Đây là một Composable, đại diện cho 1 "hàng" (item) trong danh sách
// Nó thay thế cho file item_task.xml và ViewHolder
@Composable
fun TaskItem(
    task: Task,
    onClick: () -> Unit // Lambda để "bắn" sự kiện click ra ngoài
) {
    Card(
        modifier = Modifier
            .fillMaxWidth() // Chiếm hết chiều ngang
            .padding(horizontal = 16.dp, vertical = 8.dp) // Cách lề
            .clickable(onClick = onClick) // Thêm hành động click
    ) {
        Column(
            modifier = Modifier.padding(16.dp) // Nội dung bên trong card
        ) {
            // Hiển thị Title
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium // Dùng font chữ có sẵn
            )

            Spacer(modifier = Modifier.height(4.dp)) // Khoảng cách nhỏ

            // Hiển thị Status
            Text(
                text = "Status: ${task.status}",
                style = MaterialTheme.typography.bodySmall
            )

            // Bạn có thể thêm timestamp của task ở đây nếu muốn
            // Text(text = task.timestamp ?: "", ...)
        }
    }
}