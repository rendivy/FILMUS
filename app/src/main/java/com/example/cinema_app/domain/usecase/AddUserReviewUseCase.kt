package com.example.cinema_app.domain.usecase

import com.example.cinema_app.domain.repository.MoviesRepository
import javax.inject.Inject

class AddUserReviewUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend fun execute(movieId: String, reviewText: String, rating: Int, isAnonymous: Boolean) {
        moviesRepository.addReview(movieId, reviewText, rating, isAnonymous)
    }
}