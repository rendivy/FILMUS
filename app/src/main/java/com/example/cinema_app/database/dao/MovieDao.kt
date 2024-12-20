package com.example.cinema_app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cinema_app.database.entities.MovieDb


@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieDb")
    suspend fun getAllMovies(): List<MovieDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieDb: MovieDb)

}