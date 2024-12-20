package com.example.cinema_app.presentation.entities

data class FavouriteMovieUi(
    val id: String,
    val name: String,
    val poster: String,
    val userReview: Int? = null,
)