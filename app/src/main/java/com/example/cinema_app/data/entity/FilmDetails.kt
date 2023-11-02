package com.example.cinema_app.data.entity

import kotlinx.serialization.Serializable


@Serializable
data class FilmDetails(
    val ageLimit: Int,
    val budget: Int,
    val country: String,
    val description: String,
    val director: String,
    val fees: Int,
    val genres: List<GenreX>,
    val id: String,
    val name: String,
    val poster: String,
    val reviews: List<ReviewX>,
    val tagline: String,
    val time: Int,
    val year: Int
)