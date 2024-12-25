package com.example.feature_filmus_movie_impl.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.feature_filmus_movie_impl.database.entities.RatingsDb


@Dao
interface RatingDao {

    @Query("SELECT filmId, userRating FROM RatingsDb")
    suspend fun getAllUserRatings(): List<RatingsDb>

    @Query("SELECT * FROM RatingsDb WHERE filmId = :filmId")
    suspend fun getUserRating(filmId: String): RatingsDb?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserRating(ratingsDb: RatingsDb)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUserRating(ratingsDb: RatingsDb)

    @Query("DELETE FROM RatingsDb")
    suspend fun deleteAllUserRatings()
}
