package com.example.cinema_app.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginBody(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
)