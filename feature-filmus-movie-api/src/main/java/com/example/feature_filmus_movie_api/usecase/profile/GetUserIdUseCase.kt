package com.example.feature_filmus_movie_api.usecase.profile

import com.example.feature_filmus_movie_api.repository.ProfileRepository


class GetUserIdUseCase (private val profileRepository: ProfileRepository) {

    suspend fun execute(): String {
        return profileRepository.getProfileData().id
    }
}
