package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.repository.FavouriteMovieRepositoryImpl
import javax.inject.Inject

class AddFavouriteMovieUseCase @Inject constructor(
    private val favouriteMovieRepositoryImpl: FavouriteMovieRepositoryImpl
) {

    suspend fun execute(movieId: String) {
        favouriteMovieRepositoryImpl.addFavouriteMovie(movieId = movieId)
    }
}