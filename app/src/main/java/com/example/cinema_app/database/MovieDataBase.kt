package com.example.cinema_app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cinema_app.database.converters.GenreConverters
import com.example.cinema_app.database.dao.MovieDao
import com.example.cinema_app.database.dao.RatingDao
import com.example.cinema_app.database.entities.MovieDb
import com.example.cinema_app.database.entities.RatingsDb


@Database(entities = [RatingsDb::class, MovieDb::class], version = 1)
@TypeConverters(GenreConverters::class)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun ratingDao(): RatingDao

    abstract fun movieDao(): MovieDao
}