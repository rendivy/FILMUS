package com.example.cinema_app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cinema_app.data.dbentity.MovieDb


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_cached")
    fun getAllMovies(): List<MovieDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieDb: MovieDb)

}