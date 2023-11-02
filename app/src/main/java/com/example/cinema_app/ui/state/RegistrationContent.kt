package com.example.cinema_app.ui.state

import com.example.cinema_app.common.Constants


data class RegistrationContent(
    val name: String,
    val nameError: String? = null,
    val email: String,
    val emailError: String? = null,
    val birthDate: String = Constants.EMPTY_STRING,
    val gender: Int = Constants.DEFAULT_SEX_INDEX,
    val password: String,
    val passwordError: String? = null,
    val login: String,
    val loginError: String? = null,
    val confirmPassword: String = Constants.EMPTY_STRING,
    val confirmPasswordError: String? = null,
    val birthDateError: String? = null
)