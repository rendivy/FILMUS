package com.example.feature_filmus_movie_api.entities.movie

import com.example.feature_filmus_movie_api.entities.Genre
import com.example.feature_filmus_movie_api.entities.Review

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
)