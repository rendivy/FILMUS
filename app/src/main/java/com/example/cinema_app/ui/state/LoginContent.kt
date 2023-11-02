package com.example.cinema_app.ui.state

data class LoginContent(
    val username: String,
    val usernameError: String? = null,
    val password: String,
    val passwordError: String? = null,
    val networkError: String? = null
)