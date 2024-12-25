package com.example.feature_filmus_movie_impl.database.converters

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