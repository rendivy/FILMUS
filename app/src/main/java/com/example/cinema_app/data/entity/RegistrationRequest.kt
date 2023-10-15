package com.example.cinema_app.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest(
    val userName: String,
    val name: String,
    val password: String,
    val email: String,
)