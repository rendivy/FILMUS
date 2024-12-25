package com.example.feature_filmus_movie_impl.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_filmus_movie_impl.database.entities.MovieDb


@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieDb")
    suspend fun getAllMovies(): List<MovieDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieDb: MovieDb)

}