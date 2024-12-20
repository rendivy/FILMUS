package com.example.cinema_app.data.review.entities

import kotlinx.serialization.Serializable

@Serializable
data class ReviewShortApi(
    val id: String,
    val rating: Int,
)
