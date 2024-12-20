package com.example.cinema_app.ui.state


data class ProfileContent(
    val id: String = "",
    val login: String = "",
    val name: String = "",
    val nameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val gender: Int = 0,
    val birthDate: String = "",
    val unexpectedError: String? = null,
    val userAvatar: String? = null,
)