package com.example.cinema_app.data.entity

import com.example.cinema_app.common.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationBody(
    @SerialName("userName")
    val userName: String,
    @SerialName("name")
    val name: String,
    @SerialName("password")
    val password: String,
    @SerialName("email")
    val email: String,
    @SerialName("birthDate")
    val birthDate: String = Constants.EMPTY_STRING,
    @SerialName("gender")
    val gender: Int = 0
)