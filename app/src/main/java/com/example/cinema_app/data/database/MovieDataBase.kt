package com.example.cinema_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cinema_app.data.dao.MovieDao
import com.example.cinema_app.data.dao.UserRatingDao
import com.example.cinema_app.data.dbentity.MovieDb
import com.example.cinema_app.data.dbentity.UserDb


@Database(entities = [UserDb::class, MovieDb::class], version = 1)
@TypeConverters(GenreConverters::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun userDao(): UserRatingDao

    abstract fun movieDao(): MovieDao
}