package com.example.feature_filmus_movie_impl.data.review.entities

import com.example.feature_filmus_movie_impl.data.entity.AuthorApi
import kotlinx.serialization.Serializable

@Serializable
class ReviewApi(
    val author: AuthorApi?,
    val createDateTime: String,
    val id: String,
    val isAnonymous: Boolean,
    val rating: Int,
    val reviewText: String,
)
