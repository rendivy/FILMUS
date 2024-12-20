package com.example.cinema_app.data.movie.entities

import com.example.cinema_app.data.review.entities.ReviewShortApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieShortApi(
    val id: String,
    val country: String,
    val name: String,
    val poster: String,
    @SerialName("reviews")
    val reviewShortList: List<ReviewShortApi>,
    @SerialName("genres")
    val genreApis: List<GenreApi>,
    val year: Int,
)

