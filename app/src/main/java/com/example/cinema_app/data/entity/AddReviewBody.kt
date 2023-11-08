package com.example.cinema_app.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class AddReviewBody(
    val reviewText: String,
    val rating: Int,
    val isAnonymous: Boolean
)