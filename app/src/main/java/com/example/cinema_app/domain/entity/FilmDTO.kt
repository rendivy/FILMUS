package com.example.cinema_app.domain.entity

import com.example.cinema_app.data.entity.Genre
import com.example.cinema_app.data.entity.Review
import com.example.cinema_app.data.entity.ReviewX

data class FilmDTO (
    val country: String,
    val genres: List<Genre>,
    val id: String,
    val name: String,
    val poster: String,
    val reviews: List<Review>,
    val year: Int,
    val userReview: ReviewX? = null,
    val filmRating: Double,
)