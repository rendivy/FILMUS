package com.example.feature_filmus_movie_api.usecase

import com.example.feature_filmus_movie_api.repository.FavouriteMovieRepository

class DeleteFavouriteMovieUseCase (private val favouriteMovieRepository: FavouriteMovieRepository) {
    suspend fun execute(movieId: String) {
        favouriteMovieRepository.deleteFavouriteMovie(movieId = movieId)
    }
}
