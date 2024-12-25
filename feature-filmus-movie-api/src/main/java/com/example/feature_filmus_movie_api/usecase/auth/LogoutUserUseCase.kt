package com.example.feature_filmus_movie_api.usecase.auth

import com.example.feature_filmus_movie_api.repository.ProfileRepository

class LogoutUserUseCase (private val profileRepository: ProfileRepository) {

    suspend fun execute() {
        profileRepository.logout()
    }
}