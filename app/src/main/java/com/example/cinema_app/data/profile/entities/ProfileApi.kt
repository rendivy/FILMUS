package com.example.cinema_app.data.profile.entities

import kotlinx.serialization.Serializable

@Serializable
data class ProfileApi(
    val avatarLink: String?,
    val birthDate: String,
    val email: String,
    val gender: Int,
    val id: String,
    val name: String,
    val nickName: String
)