package com.example.feature_filmus_movie_impl.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.feature_filmus_movie_impl.database.converters.GenreConverters
import com.example.feature_filmus_movie_impl.database.dao.MovieDao
import com.example.feature_filmus_movie_impl.database.dao.RatingDao
import com.example.feature_filmus_movie_impl.database.entities.MovieDb
import com.example.feature_filmus_movie_impl.database.entities.RatingsDb


@Database(entities = [RatingsDb::class, MovieDb::class], version = 1)
@TypeConverters(GenreConverters::class)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun ratingDao(): RatingDao

    abstract fun movieDao(): MovieDao
}