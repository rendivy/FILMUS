package com.example.feature_filmus_movie_api.entities

/**
 * @author y.yangel
 */
class Review(
    val author: Author?,
    val id: String,
    val createDateTime: String,
    val rating: Int,
    val isAnonymous: Boolean,
    val reviewText: String,
)

class ReviewShort(
    val id: String,
    val rating: Int,
)