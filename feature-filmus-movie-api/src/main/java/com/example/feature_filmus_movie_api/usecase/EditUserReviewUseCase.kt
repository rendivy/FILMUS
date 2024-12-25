package com.example.feature_filmus_movie_api.usecase

import com.example.feature_filmus_movie_api.repository.MoviesRepository

class EditUserReviewUseCase(private val movieDetailsRepository: MoviesRepository) {

    suspend fun execute(
        movieId: String,
        reviewId: String,
        reviewText: String,
        rating: Float,
        isAnonymous: Boolean,
    ) {
        movieDetailsRepository.editReview(
            movieId = movieId,
            reviewId = reviewId,
            rating = rating.toInt(),
            isAnonymous = isAnonymous,
            reviewText = reviewText,
        )
    }
}
