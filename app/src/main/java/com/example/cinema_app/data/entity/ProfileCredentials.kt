package com.example.cinema_app.data.entity

import kotlinx.serialization.Serializable


@Serializable
data class ProfileCredentials(
    val avatarLink: String?,
    val birthDate: String,
    val email: String,
    val gender: Int,
    val id: String,
    val name: String,
    val nickName: String
)