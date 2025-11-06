package com.example.api.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Đây là một Singleton Object
// Nó chỉ được tạo ra MỘT LẦN duy nhất trong suốt vòng đời ứng dụng
object RetrofitClient {

    // Địa chỉ gốc của API
    private const val BASE_URL = "https://amock.io/"

    // Khởi tạo Retrofit
    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Dùng Gson để tự động chuyển JSON (text) thành đối tượng Task (Kotlin)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Tạo ra "bản thể" của ApiService
        retrofit.create(ApiService::class.java)
    }
}