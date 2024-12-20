package com.example.cinema_app.domain.usecase

import com.example.cinema_app.domain.repository.MoviesRepository
import javax.inject.Inject

class EditUserReviewUseCase @Inject constructor(private val movieDetailsRepository: MoviesRepository) {

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
