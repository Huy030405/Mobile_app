package com.example.api.data.network

import com.example.api.data.model.Task
import com.example.api.data.model.TaskResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

// Interface này định nghĩa các "cửa" (endpoints) của API
interface ApiService {

    // GET https://amock.io/api/researchUTH/tasks
    @GET("api/researchUTH/tasks")
    suspend fun getAllTasks(): TaskResponse

    // GET https://amock.io/api/researchUTH/task/1
    @GET("api/researchUTH/task/{id}")
    suspend fun getTaskDetail(@Path("id") taskId: String): Task

    // DEL https://amock.io/api/researchUTH/task/1
    @DELETE("api/researchUTH/task/{id}")
    suspend fun deleteTask(@Path("id") taskId: String): Response<Unit> // Dùng Response<Unit> để check xem xoá thành công không
}