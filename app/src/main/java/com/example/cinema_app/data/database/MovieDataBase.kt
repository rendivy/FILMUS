package com.example.cinema_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cinema_app.data.dao.UserRatingDao
import com.example.cinema_app.data.dbentity.UserRating


@Database(entities = [UserRating::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun userDao(): UserRatingDao
}