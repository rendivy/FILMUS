package com.example.cinema_app.data.api.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LoginApi(
    @SerialName("username")
    val login: String,
    @SerialName("password")
    val password: String,
)