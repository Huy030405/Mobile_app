package com.example.api.data.model
import com.google.gson.annotations.SerializedName

// Đặt tên "data" hoặc "tasks" cho khớp với API
data class TaskResponse(
    @SerializedName("data") // Sửa tên này cho khớp
    val taskList: List<Task>
)