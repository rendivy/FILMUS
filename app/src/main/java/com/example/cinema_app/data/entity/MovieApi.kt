package com.example.cinema_app.data.entity

import kotlinx.serialization.Serializable

@Serializable
internal class MovieApi(val movies: List<FilmShort>)