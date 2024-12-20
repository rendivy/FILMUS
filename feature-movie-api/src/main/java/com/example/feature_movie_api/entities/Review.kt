package com.example.feature_movie_api.entities

import kotlinx.serialization.Serializable

/**
 * @author y.yangel
 */
@Serializable
class Review(
    val author: Author?,
    val id: String,
    val createDateTime: String,
    val rating: Int,
    val isAnonymous: Boolean,
    val reviewText: String,
)
