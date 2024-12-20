package com.example.cinema_app.domain.entities.movie

import com.example.cinema_app.domain.entities.Genre
import com.example.cinema_app.domain.entities.Review
import com.example.cinema_app.domain.entities.ReviewShort

class Movie(
    val ageLimit: Int,
    val budget: Int?,
    val country: String,
    val description: String,
    val director: String,
    val fees: Int?,
    val id: String,
    val name: String,
    val poster: String,
    val tagline: String,
    val time: Int,
    val year: Int,
    val genres: List<Genre> = listOf(),
    val review: List<Review> = listOf(),
)

class MovieShort(
    val id: String,
    val country: String,
    val name: String,
    val poster: String,
    val year: Int,
    val genres: List<Genre> = listOf(),
    val reviewShortList: List<ReviewShort> = listOf(),
)

class MovieWithRatings(
    val id: String,
    val country: String,
    val name: String,
    val poster: String,
    val year: Int,
    val genres: List<Genre> = listOf(),
    val userRating: Int? = null,
    val reviewShortList: List<ReviewShort> = listOf(),
    val filmRating: Double? = null,
)