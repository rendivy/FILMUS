package com.example.cinema_app.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest(
    @SerialName("userName") val userName: String,
    @SerialName("name") val name: String,
    @SerialName("password") val password: String,
    @SerialName("email") val email: String,
)