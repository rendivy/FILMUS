package com.example.cinema_app.data.dbentity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nullable


@Entity(tableName = "movie_cached")
data class MovieCached(

    @ColumnInfo(name = "film_name")
    @PrimaryKey
    val filmName: String,
    @ColumnInfo(name = "user_rating")
    @Nullable
    val userRating: Int?,
    @ColumnInfo(name = "film_contry")
    val filmCountry: String,
    @ColumnInfo(name = "film_year")
    val filmYear: Int,
    @ColumnInfo(name = "film_genres")
    val filmGenres: List<String>
)
