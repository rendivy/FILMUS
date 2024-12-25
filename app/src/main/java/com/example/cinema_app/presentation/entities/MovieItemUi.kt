package com.example.cinema_app.presentation.entities

import com.example.feature_filmus_movie_api.entities.Genre
import com.example.feature_filmus_movie_api.entities.ReviewShort

data class MovieItemUi (
    val country: String,
    val genreApis: List<Genre>,
    val id: String,
    val name: String,
    val poster: String,
    val reviewShortApis: List<ReviewShort>,
    val year: Int,
    val userReview: Int? = null,
    val filmRating: Double,
)