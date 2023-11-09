package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.repository.MoviesRepositoryImpl
import com.example.cinema_app.ui.state.ReviewContent
import javax.inject.Inject

class EditUserReviewUseCase @Inject constructor(
    private val movieDetailsRepositoryImpl: MoviesRepositoryImpl
) {
    suspend fun execute(review: ReviewContent, movieId: String, reviewId: String) {
        movieDetailsRepositoryImpl.editReview(movieId, reviewId, review)
    }
}