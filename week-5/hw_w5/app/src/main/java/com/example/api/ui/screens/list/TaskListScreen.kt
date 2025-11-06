package com.example.api.ui.screens.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.api.ui.screens.list.components.EmptyView
import com.example.api.ui.screens.list.components.TaskItem
import com.example.api.viewmodel.TaskViewModel

// Đây là Composable cho toàn bộ Màn hình Danh sách
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onTaskClick: (String) -> Unit // <-- ĐÂY LÀ DÒNG SỬA QUAN TRỌNG NHẤT
) {
    // "observeAsState" tự động lắng nghe LiveData từ ViewModel
    val tasks by viewModel.tasks.observeAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val isEmpty by viewModel.isEmpty.observeAsState(initial = false)

    // "LaunchedEffect" dùng để gọi một suspend function (như fetchAllTasks)
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchAllTasks()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        if (isLoading) {
            CircularProgressIndicator()
        } else if (isEmpty) {
            EmptyView()
        } else {
            // LazyColumn thay thế cho RecyclerView
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                // "items" lặp qua danh sách "tasks"
                items(tasks) { task ->
                    // Với mỗi "task", vẽ một "TaskItem"
                    TaskItem(
                        task = task,
                        onClick = {
                            // Khi item được click, gọi hàm onTaskClick
                            // và TRUYỀN task.id (String) vào
                            onTaskClick(task.id)
                        }
                    )
                }
            }
        }
    }
}