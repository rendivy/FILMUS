package com.example.cinema_app.data.entity

import com.google.gson.annotations.SerializedName

data class ErrorMessage(
    @SerializedName("message")
    val message: String? = null
)