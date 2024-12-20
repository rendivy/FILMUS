package com.example.cinema_app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.cinema_app.data.dbentity.UserDb


@Dao
interface UserRatingDao {

    @Query("SELECT filmId, user_rating FROM user_ratings")
    fun getAllUserRatings(): List<UserDb>

    @Query("SELECT * FROM user_ratings WHERE filmId = :filmId")
    fun getUserRating(filmId: String): UserDb?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserRating(userDb: UserDb)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUserRating(userDb: UserDb)

    @Query("DELETE FROM user_ratings")
    fun deleteAllUserRatings()

}