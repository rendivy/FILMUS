package com.example.cinema_app.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val id: String,
    val rating: Int
)