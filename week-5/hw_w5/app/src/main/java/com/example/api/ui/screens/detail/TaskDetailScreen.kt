package com.example.api.ui.screens.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.api.viewmodel.TaskViewModel

// Đây là Composable cho Màn hình Chi tiết
@Composable
fun TaskDetailScreen(
    viewModel: TaskViewModel,
    taskId: String?, // ID nhận từ AppNavigation
    onNavigateBack: () -> Unit // Hàm quay về (do AppNavigation đưa vào)
) {
    // Lắng nghe các "khay" (LiveData) từ ViewModel
    val taskDetail by viewModel.taskDetail.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val deleteSuccess by viewModel.deleteSuccess.observeAsState(initial = false)

    // Lấy context (dùng để hiển thị Toast)
    val context = LocalContext.current

    // "LaunchedEffect" với key là "taskId"
    // Nó sẽ chạy 1 lần khi màn hình mở, VÀ chạy lại nếu "taskId" thay đổi
    LaunchedEffect(key1 = taskId) {
        if (taskId != null) {
            // Yêu cầu của slide: "màn hình chi tiết phải gọi API Chi tiết"
            viewModel.fetchTaskDetail(taskId)
        }
    }

    // "LaunchedEffect" với key là "deleteSuccess"
    // Nó sẽ chạy lại mỗi khi "deleteSuccess" thay đổi
    LaunchedEffect(key1 = deleteSuccess) {
        if (deleteSuccess) {
            Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show()
            viewModel.resetDeleteSuccessFlag()
            onNavigateBack() // Gọi hàm để quay về màn hình List
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading || taskDetail == null) {
            // 1. Nếu đang load HOẶC chưa có data -> Hiện cục xoay
            CircularProgressIndicator()

        } else {
            // 2. Nếu đã có data -> Hiển thị chi tiết
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Hiển thị Title
                Text(
                    text = taskDetail?.title ?: "Loading...", // Dùng "?:" để tránh lỗi null
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Hiển thị Description
                Text(
                    text = taskDetail?.description ?: "No description provided.",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Hiển thị Status, Priority... (bạn tự thêm nếu muốn)
                Text(text = "Status: ${taskDetail?.status}")
                Text(text = "Priority: ${taskDetail?.priority}")

                Spacer(modifier = Modifier.weight(1f)) // "Đẩy" nút Xoá xuống dưới cùng

                // Nút Xoá (DEL API)
                Button(
                    onClick = {
                        if (taskId != null) {
                            // Yêu cầu của slide: "Nhấn xoá thì gọi API xoá"
                            viewModel.deleteTask(taskId)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Xoá Task (DEL)")
                }
            }
        }
    }
}