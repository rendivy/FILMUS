package com.example.cinema_app.data.entity

import kotlinx.serialization.Serializable


@Serializable
data class ReviewX(
    val author: Author?,
    val createDateTime: String,
    val id: String,
    val isAnonymous: Boolean,
    val rating: Int,
    val reviewText: String
)