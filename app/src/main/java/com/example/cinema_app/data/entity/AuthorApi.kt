package com.example.cinema_app.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class AuthorApi(
    val avatar: String?,
    val nickName: String,
    val userId: String,
)