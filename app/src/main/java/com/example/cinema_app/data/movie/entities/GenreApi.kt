package com.example.cinema_app.data.movie.entities

import kotlinx.serialization.Serializable

@Serializable
data class GenreApi(
    val id: String,
    val name: String,
)