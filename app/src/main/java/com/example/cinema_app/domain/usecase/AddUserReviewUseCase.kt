package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.repository.MoviesRepositoryImpl
import javax.inject.Inject

class AddUserReviewUseCase @Inject constructor(private val moviesRepositoryImpl: MoviesRepositoryImpl) {

    suspend fun execute(movieId: String, reviewText: String, rating: Int, isAnonymous: Boolean) {
        moviesRepositoryImpl.addReview(movieId, reviewText, rating, isAnonymous)
    }
}