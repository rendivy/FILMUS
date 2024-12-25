package com.example.feature_filmus_movie_api.usecase.profile

import com.example.feature_filmus_movie_api.entities.profile.Profile
import com.example.feature_filmus_movie_api.repository.ProfileRepository

class UpdateUserProfileUseCase(private val repository: ProfileRepository) {
    suspend fun execute(profile: Profile) {
        repository.updateProfileData(profile)
    }
}
