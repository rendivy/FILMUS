package com.example.feature_movie_api.entities.details

import com.example.feature_movie_api.entities.Genre
import com.example.feature_movie_api.entities.Review

/**
 * @author y.yangel
 */
class FilmDetails(
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