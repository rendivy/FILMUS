package com.example.feature_filmus_movie_api.usecase.profile

import com.example.feature_filmus_movie_api.entities.profile.Profile
import com.example.feature_filmus_movie_api.repository.ProfileRepository

class GetUserProfileUseCase(
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(): Profile {
        return profileRepository.getProfileData()
    }
}
