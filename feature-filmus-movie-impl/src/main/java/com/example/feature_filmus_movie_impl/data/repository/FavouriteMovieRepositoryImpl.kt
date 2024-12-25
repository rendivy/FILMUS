package com.example.feature_filmus_movie_impl.data.repository

import com.example.feature_filmus_movie_api.entities.movie.FavouriteMovie
import com.example.feature_filmus_movie_api.repository.FavouriteMovieRepository
import com.example.feature_filmus_movie_impl.data.api.MovieApiService
import com.example.feature_filmus_movie_impl.data.review.mapper.toDomain
import com.example.feature_filmus_movie_impl.data.storage.TokenLocalStorage
import javax.inject.Inject

internal class FavouriteMovieRepositoryImpl @Inject constructor(
    private val tokenLocalStorage: TokenLocalStorage,
    private val movieApiService: MovieApiService,
) : FavouriteMovieRepository {

    override suspend fun getFavouriteMovie(): FavouriteMovie {
        val token = tokenLocalStorage.getToken()
        return movieApiService.getFavoriteMovies(token = "Bearer $token").toDomain()
    }

    override suspend fun deleteFavouriteMovie(movieId: String) {
        val token = tokenLocalStorage.getToken()
        movieApiService.deleteFavouriteMovie(token = "Bearer $token", movieId = movieId )
    }

    override suspend fun addFavouriteMovie(movieId: String) {
        val token = tokenLocalStorage.getToken()
        movieApiService.addFavouriteMovie(token = "Bearer $token", movieId = movieId)
    }
}