package com.example.cinema_app.domain.entities.movie

import com.example.cinema_app.domain.entities.Genre
import com.example.cinema_app.domain.entities.Review

/**
 * @author y.yangel
 */
class MovieDetails(
    val ageLimit: Int,
    val budget: Int?,
    val country: String,
    val description: String,
    val director: String,
    val fees: Int?,
    val genres: List<Genre>,
    val id: String,
    val name: String,
    val poster: String,
    val tagline: String,
    val time: Int,
    val year: Int,
    val reviews: List<Review>,
    val filmInFavourite: Boolean,
    val averageFilmRating: Double,
    val userReview: Review? = null,
)