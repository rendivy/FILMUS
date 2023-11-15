package com.example.cinema_app.domain.usecase

import com.example.cinema_app.domain.repository.MoviesRepository
import javax.inject.Inject

class DeleteUserReviewUseCase @Inject constructor(
    private val  moviesRepository: MoviesRepository
){
    suspend fun execute(movieId: String, reviewId: String) {
        moviesRepository.deleteReview(movieId, reviewId)
    }
}