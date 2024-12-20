package com.example.cinema_app.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class ReviewShortApi(
    val id: String,
    val rating: Int
)