package com.example.cinema_app.domain.entity

import androidx.compose.runtime.Immutable
import com.example.cinema_app.data.entity.Genre
import com.example.cinema_app.data.entity.ReviewShortApi

@Immutable
data class FilmItemUi (
    val country: String,
    val genres: List<Genre>,
    val id: String,
    val name: String,
    val poster: String,
    val reviewShortApis: List<ReviewShortApi>,
    val year: Int,
    val userReview: Int? = null,
    val filmRating: Double,
)