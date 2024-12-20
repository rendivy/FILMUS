package com.example.cinema_app.presentation.entities

import com.example.cinema_app.domain.entities.Genre
import com.example.cinema_app.domain.entities.Review

data class MovieDetailsUi(
    val ageLimit: Int,
    val budget: Int?,
    val country: String,
    val description: String,
    val director: String,
    val fees: Int?,
    val genreApis: List<Genre>,
    val id: String,
    val name: String,
    val poster: String,
    val review: List<Review>,
    val tagline: String,
    val time: Int,
    val year: Int,
    val filmInFavourite: Boolean,
    val averageFilmRating: Double,
    val userReviewApiX: Review? = null,
)
