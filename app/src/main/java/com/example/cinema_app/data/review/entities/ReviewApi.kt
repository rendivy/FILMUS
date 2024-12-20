package com.example.cinema_app.data.review.entities

import com.example.cinema_app.data.entity.AuthorApi
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
