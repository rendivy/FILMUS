package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.entity.Review
import javax.inject.Inject

class GetUserReviewUseCase @Inject constructor(private val userProfileUseCase: GetUserProfileUseCase) {

    suspend fun execute(reviews: List<Review>): Int? {
        val profile = userProfileUseCase.execute()
        reviews.forEach {
            if (it.id == profile.id) {
                return it.rating
            }
        }
        return null
    }
}