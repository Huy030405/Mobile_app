package com.example.api.ui.navigation

// Object này chứa các "hằng số" (const) là tên của các màn hình
// Dùng cái này để đảm bảo chúng ta gõ đúng tên ở mọi nơi
object ScreenRoute {
    // Tên màn hình danh sách
    const val LIST = "task_list"

    // Tên màn hình chi tiết (gốc)
    const val DETAIL = "task_detail"

    // Tên của tham số (argument) mà màn hình chi tiết cần
    const val DETAIL_ARG_ID = "taskId"

    // Route đầy đủ của màn hình chi tiết, bao gồm cả tham số
    // Nó sẽ có dạng "task_detail/some-task-id"
    const val DETAIL_ROUTE = "$DETAIL/{$DETAIL_ARG_ID}"
}