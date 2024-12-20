package com.example.feature_movie_api.entities

import kotlinx.serialization.Serializable

/**
 * @author y.yangel
 */
@Serializable
class Author(
    val avatar: String,
    val nickName: String,
    val userId: String,
)
