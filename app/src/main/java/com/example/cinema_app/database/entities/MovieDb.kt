package com.example.cinema_app.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MovieDb(
    @PrimaryKey
    val id: String,
    val name: String,
    val filmRating: Double?,
    val userRating: Int?,
    val country: String,
    val year: Int,
    val genres: List<String>,
)
