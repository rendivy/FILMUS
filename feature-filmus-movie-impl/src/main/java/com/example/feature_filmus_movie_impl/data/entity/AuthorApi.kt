package com.example.feature_filmus_movie_impl.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class AuthorApi(
    val avatar: String?,
    val nickName: String,
    val userId: String,
)