package com.example.cinema_app.domain.repository

import com.example.cinema_app.data.entity.FavouriteMovieApi

interface FavouriteMovieRepository {

    suspend fun getFavouriteMovie(): FavouriteMovieApi

    suspend fun deleteFavouriteMovie(movieId: String)

    suspend fun addFavouriteMovie(movieId: String)
}