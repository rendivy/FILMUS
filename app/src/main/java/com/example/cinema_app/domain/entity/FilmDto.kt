package com.example.cinema_app.domain.entity

import com.example.cinema_app.data.entity.Genre
import com.example.cinema_app.data.entity.Review

data class FilmDto (
    val country: String,
    val genres: List<Genre>,
    val id: String,
    val name: String,
    val poster: String,
    val reviews: List<Review>,
    val year: Int,
    val filmRating: Double,
    val userRating: Int? = null
)