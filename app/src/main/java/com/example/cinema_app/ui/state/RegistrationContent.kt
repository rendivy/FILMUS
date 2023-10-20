package com.example.cinema_app.ui.state

import com.example.cinema_app.common.Constants.EMPTY_STRING

data class RegistrationContent(
    val name: String,
    val login: String,
    val password: String,
    val email: String,
    val birthDate: String = EMPTY_STRING,
)