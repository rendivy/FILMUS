package com.example.cinema_app.data.api.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RegistrationApi(
    @SerialName("userName")
    val userName: String,
    @SerialName("name")
    val name: String,
    @SerialName("password")
    val password: String,
    @SerialName("email")
    val email: String,
    @SerialName("birthDate")
    val birthDate: String,
    @SerialName("gender")
    val gender: Int = 0,
)