package com.example.cinema_app.ui.state

import com.example.cinema_app.common.Constants

data class ProfileContent(
    val id: String,
    val login: String,
    val name: String,
    val nameError: String? = null,
    val email: String,
    val gender: Int ,
    val emailError: String? = null,
    val birthDate: String = Constants.EMPTY_STRING,
    val userAvatar: String = Constants.EMPTY_STRING,
)