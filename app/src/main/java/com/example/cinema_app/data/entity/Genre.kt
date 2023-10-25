package com.example.cinema_app.data.entity

import kotlinx.serialization.Serializable


@Serializable
data class Genre(
    val id: String,
    val name: String
)