package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.entity.ProfileCredentials
import com.example.cinema_app.data.entity.ReviewX
import com.example.cinema_app.domain.repository.ProfileRepository
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
) {

    suspend fun execute(): ProfileCredentials {
        return profileRepository.getProfileData()
    }


    suspend fun getUserReview(filmReviews: List<ReviewX>): ReviewX? {
        val profile = execute()
        filmReviews.forEach {
            if (it.author != null) {
                if (it.author.userId == profile.id) {
                    return it
                }
            }

        }
        return null
    }
}