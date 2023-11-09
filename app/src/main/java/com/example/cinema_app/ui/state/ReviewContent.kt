package com.example.cinema_app.ui.state

data class ReviewContent(
    val reviewText: String,
    val rating: Float,
    val isAnonymous: Boolean = false,
)