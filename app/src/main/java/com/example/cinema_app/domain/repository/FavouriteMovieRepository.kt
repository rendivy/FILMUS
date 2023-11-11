package com.example.cinema_app.domain.repository

import com.example.cinema_app.data.entity.Movie

interface FavouriteMovieRepository {

    suspend fun getFavouriteMovie(): Movie

    suspend fun addFavouriteMovie(movieId: String)
}