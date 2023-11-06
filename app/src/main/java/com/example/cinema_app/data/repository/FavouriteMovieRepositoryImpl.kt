package com.example.cinema_app.data.repository

import com.example.cinema_app.data.entity.Movie
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.storage.TokenLocalStorage
import com.example.cinema_app.domain.repository.FavouriteMovieRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FavouriteMovieRepositoryImpl @Inject constructor(
    private val tokenLocalStorage: TokenLocalStorage,
    private val movieApiService: MovieApiService
) : FavouriteMovieRepository {

    override suspend fun getFavouriteMovie(): Movie {
        val token = tokenLocalStorage.getToken()
        return movieApiService.getFavoriteMovies(token = "Bearer $token")
    }
}