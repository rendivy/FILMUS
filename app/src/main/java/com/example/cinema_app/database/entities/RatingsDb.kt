package com.example.cinema_app.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RatingsDb(
    @PrimaryKey
    val filmId: String,
    val userRating: Int?,
)