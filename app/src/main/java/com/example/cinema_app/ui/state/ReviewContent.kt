package com.example.cinema_app.ui.state

data class ReviewContent(
    val reviewText: String,
    val rating: Float,
    val isRatingChanged: Boolean? = null,
    val isAnonymous: Boolean = false,
    val anonymousError: String? = null,
)