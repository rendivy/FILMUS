package com.example.cinema_app.data.entity

data class RegistrationRequest(
    val userName: String,
    val name: String,
    val password: String,
    val email: String,
    val birthDate: String,
    val gender: Int = 0
)