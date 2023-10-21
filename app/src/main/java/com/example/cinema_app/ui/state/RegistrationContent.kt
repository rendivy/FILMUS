package com.example.cinema_app.ui.state

import com.example.cinema_app.common.Constants


data class RegistrationContent(
    val name: String,
    val login: String,
    val password: String,
    val passwordError: String? = null,
    val email: String,
    val confirmPassword: String? = null,
    val confirmPasswordError: String? = null,
    val gender: Int = Constants.DEFAULT_SEX_INDEX,
    val birthDate: String = Constants.EMPTY_STRING,
)