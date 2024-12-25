package com.example.feature_filmus_movie_impl.data.movie.entities

import com.example.feature_filmus_movie_impl.data.review.entities.ReviewApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsApi(
    val ageLimit: Int,
    val budget: Int?,
    val country: String,
    val description: String,
    val director: String,
    val fees: Int?,
    @SerialName("genres")
    val genreApis: List<GenreApi>,
    val id: String,
    val name: String,
    val poster: String,
    @SerialName("reviews")
    val reviewApis: List<ReviewApi>,
    val tagline: String,
    val time: Int,
    val year: Int,
)