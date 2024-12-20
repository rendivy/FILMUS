package com.example.cinema_app.domain.usecase.profile

import com.example.cinema_app.domain.repository.ProfileRepository
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(): com.example.cinema_app.domain.entities.profile.Profile {
        return profileRepository.getProfileData()
    }
}