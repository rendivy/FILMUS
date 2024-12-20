package com.example.cinema_app.domain.repository

import com.example.cinema_app.data.entity.FilmShort

interface FavouriteMovieRepository {

    suspend fun getFavouriteMovie(): List<FilmShort>

    suspend fun deleteFavouriteMovie(movieId: String)

    suspend fun addFavouriteMovie(movieId: String)
}