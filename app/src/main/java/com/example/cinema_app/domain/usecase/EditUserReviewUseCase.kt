package com.example.cinema_app.domain.usecase

import com.example.cinema_app.domain.repository.MoviesRepository
import com.example.cinema_app.ui.state.ReviewContent
import javax.inject.Inject

class EditUserReviewUseCase @Inject constructor(
    private val movieDetailsRepository: MoviesRepository
) {
    suspend fun execute(review: ReviewContent, movieId: String, reviewId: String) {
        movieDetailsRepository.editReview(movieId, reviewId, review)
    }
}