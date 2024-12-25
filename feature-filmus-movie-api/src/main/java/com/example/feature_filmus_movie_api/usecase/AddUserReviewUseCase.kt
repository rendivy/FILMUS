package com.example.feature_filmus_movie_api.usecase

import com.example.feature_filmus_movie_api.repository.MoviesRepository

class AddUserReviewUseCase(private val moviesRepository: MoviesRepository) {

    suspend fun execute(movieId: String, reviewText: String, rating: Int, isAnonymous: Boolean) {
        moviesRepository.addReview(movieId, reviewText, rating, isAnonymous)
    }
}
