package com.example.cinema_app.data.database

import androidx.room.TypeConverter

class GenreConverters {

    @TypeConverter
    fun fromGenres(genres: List<String>): String {
        return genres.joinToString(separator = ",")
    }

    @TypeConverter
    fun toGenres(genres: String): List<String> {
        return genres.split(",")
    }
}