package com.example.feature_filmus_movie_api.usecase

import com.example.feature_filmus_movie_api.repository.FavouriteMovieRepository

class AddFavouriteMovieUseCase(private val favouriteMovieRepository: FavouriteMovieRepository) {

    suspend fun execute(movieId: String) {
        favouriteMovieRepository.addFavouriteMovie(movieId = movieId)
    }
}

