package com.example.api.data.model

import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("timestamp")
    val timestamp: String? = null, // Dùng String? nghĩa là "có thể null"

    // Các trường này có thể chỉ có ở API detail
    @SerializedName("description")
    val description: String? = null,

    @SerializedName("priority")
    val priority: String? = null,

    @SerializedName("category")
    val category: String? = null
)