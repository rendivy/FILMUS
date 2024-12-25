package com.example.feature_filmus_movie_impl.data.movie.entities

import com.example.feature_filmus_movie_impl.data.review.entities.ReviewShortApi
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

