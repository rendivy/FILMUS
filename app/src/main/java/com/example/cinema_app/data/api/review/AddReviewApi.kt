package com.example.cinema_app.data.api.review

import kotlinx.serialization.Serializable

@Serializable
class AddReviewApi(
    val reviewText: String,
    val rating: Int,
    val isAnonymous: Boolean
)