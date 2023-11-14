package com.example.cinema_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cinema_app.data.dao.MovieDao
import com.example.cinema_app.data.dao.UserRatingDao
import com.example.cinema_app.data.dbentity.MovieCached
import com.example.cinema_app.data.dbentity.UserRating


@Database(entities = [UserRating::class, MovieCached::class], version = 1)
@TypeConverters(GenreConverters::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun userDao(): UserRatingDao

    abstract fun movieDao(): MovieDao
}