package com.example.cinema_app.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class FilmShort(
    val country: String,
    val genres: List<Genre>,
    val id: String,
    val name: String,
    val poster: String,
    val reviewShortApis: List<ReviewShortApi>,
    val year: Int,
)

