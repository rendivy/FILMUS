package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.entity.ReviewShortApi
import javax.inject.Inject

class GetUserReviewUseCase @Inject constructor(private val userProfileUseCase: GetUserProfileUseCase) {

    suspend fun execute(reviewShortApis: List<ReviewShortApi>): Int? {
        val profile = userProfileUseCase.execute()
        reviewShortApis.forEach {
            if (it.id == profile.id) {
                return it.rating
            }
        }
        return null
    }
}