package com.example.cinema_app.data.dbentity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_ratings")
data class UserRating(
    @PrimaryKey
    val filmId: String,
    @ColumnInfo(name = "user_rating")
    val userRating: Int?,
)