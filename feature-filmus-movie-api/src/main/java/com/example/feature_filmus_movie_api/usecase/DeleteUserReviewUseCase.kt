package com.example.feature_filmus_movie_api.usecase

import com.example.feature_filmus_movie_api.repository.MoviesRepository

class DeleteUserReviewUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun execute(movieId: String, reviewId: String) {
        moviesRepository.deleteReview(movieId, reviewId)
    }
}
