package com.example.feature_filmus_movie_impl.data.review.entities

import kotlinx.serialization.Serializable

@Serializable
class AddReviewApi(
    val reviewText: String,
    val rating: Int,
    val isAnonymous: Boolean,
)
