package com.example.cinema_app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.cinema_app.data.dbentity.UserRating


@Dao
interface UserRatingDao {

    @Query("SELECT filmId, user_rating FROM user_ratings")
    fun getAllUserRatings(): List<UserRating>

    @Query("SELECT * FROM user_ratings WHERE filmId = :filmId")
    fun getUserRating(filmId: String): UserRating?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserRating(userRating: UserRating)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUserRating(userRating: UserRating)

    @Query("DELETE FROM user_ratings")
    fun deleteAllUserRatings()

}