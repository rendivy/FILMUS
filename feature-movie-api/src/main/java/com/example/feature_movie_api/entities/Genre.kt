package com.example.feature_movie_api.entities

import kotlinx.serialization.Serializable

/**
 * @author y.yangel
 */
@Serializable
class Genre(
    val id: String,
    val name: String,
)