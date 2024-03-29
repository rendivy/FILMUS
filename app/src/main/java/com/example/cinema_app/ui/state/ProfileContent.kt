package com.example.cinema_app.ui.state

import com.example.cinema_app.common.Constants

data class ProfileContent(
    val id: String,
    val login: String,
    val name: String,
    val nameError: String? = null,
    val email: String,
    val emailError: String? = null,
    val gender: Int ,
    val birthDate: String = Constants.EMPTY_STRING,
    val unexpectedError: String? = null,
    val userAvatar: String? = null,
)