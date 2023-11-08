package com.example.cinema_app.domain.entity

import com.example.cinema_app.data.entity.GenreX
import com.example.cinema_app.data.entity.ReviewX

data class DetailsDTO(
    val ageLimit: Int,
    val budget: Int?,
    val country: String,
    val description: String,
    val director: String,
    val fees: Int?,
    val genres: List<GenreX>,
    val id: String,
    val name: String,
    val poster: String,
    val reviews: List<ReviewX>,
    val tagline: String,
    val time: Int,
    val year: Int,
    val userReviewX: ReviewX? = null,
)
