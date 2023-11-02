package com.example.cinema_app.data.entity

import kotlinx.serialization.Serializable


@Serializable
data class Movie(
    val movies: List<Film>,
    val pageInfo: PageInfo
)