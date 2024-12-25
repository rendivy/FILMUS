package com.example.feature_filmus_movie_api.repository

import com.example.feature_filmus_movie_api.entities.movie.FavouriteMovie

interface FavouriteMovieRepository {

    suspend fun getFavouriteMovie(): FavouriteMovie

    suspend fun deleteFavouriteMovie(movieId: String)

    suspend fun addFavouriteMovie(movieId: String)
}