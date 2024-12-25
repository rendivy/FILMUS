package com.example.feature_filmus_movie_impl.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RatingsDb(
    @PrimaryKey
    val filmId: String,
    val userRating: Int?,
)