package com.example.cinema_app.data.repository

import android.util.Log
import com.example.cinema_app.data.entity.Movie
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.storage.TokenLocalStorage
import com.example.cinema_app.domain.repository.FavouriteMovieRepository
import javax.inject.Inject



class FavouriteMovieRepositoryImpl @Inject constructor(
    private val tokenLocalStorage: TokenLocalStorage,
    private val movieApiService: MovieApiService
) : FavouriteMovieRepository {

    override suspend fun getFavouriteMovie(): Movie {
        val token = tokenLocalStorage.getToken()
        Log.d("TAG", "token: $token")
        return movieApiService.getFavoriteMovies(token = "Bearer $token")
    }
}