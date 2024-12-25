package com.example.feature_filmus_movie_impl.data.review.entities

import kotlinx.serialization.Serializable

@Serializable
data class ReviewShortApi(
    val id: String,
    val rating: Int,
)
