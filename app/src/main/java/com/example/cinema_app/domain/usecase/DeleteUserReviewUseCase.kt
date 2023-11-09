package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.repository.MoviesRepositoryImpl
import javax.inject.Inject

class DeleteUserReviewUseCase @Inject constructor(
    private val  moviesRepositoryImpl: MoviesRepositoryImpl
){
    suspend fun execute(movieId: String, reviewId: String) {
        moviesRepositoryImpl.deleteReview(movieId, reviewId)
    }
}