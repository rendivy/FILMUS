package com.example.cinema_app.domain.usecase

import com.example.cinema_app.domain.repository.FavouriteMovieRepository
import javax.inject.Inject

class AddFavouriteMovieUseCase @Inject constructor(
    private val favouriteMovieRepository: FavouriteMovieRepository
) {
    suspend fun execute(movieId: String) {
        favouriteMovieRepository.addFavouriteMovie(movieId = movieId)
    }

}