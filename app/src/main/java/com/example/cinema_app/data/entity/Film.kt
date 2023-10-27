package com.example.cinema_app.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Film(
    val country: String,
    val genres: List<Genre>,
    val id: String,
    val name: String,
    val poster: String,
    val reviews: List<Review>,
    val year: Int
)