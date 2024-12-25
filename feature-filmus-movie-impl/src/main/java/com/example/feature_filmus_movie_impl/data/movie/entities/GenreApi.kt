package com.example.feature_filmus_movie_impl.data.movie.entities

import kotlinx.serialization.Serializable

@Serializable
data class GenreApi(
    val id: String,
    val name: String,
)