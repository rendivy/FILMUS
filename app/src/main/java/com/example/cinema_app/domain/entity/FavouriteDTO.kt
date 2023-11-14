package com.example.cinema_app.domain.entity

data class FavouriteDTO(
    val id: String,
    val name: String,
    val poster: String,
    val userReview: Int? = null,
)