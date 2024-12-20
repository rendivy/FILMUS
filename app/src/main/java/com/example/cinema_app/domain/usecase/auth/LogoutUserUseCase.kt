package com.example.cinema_app.domain.usecase.auth

import com.example.cinema_app.domain.repository.ProfileRepository
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(private val profileRepository: ProfileRepository) {

    suspend fun execute() {
        profileRepository.logout()
    }
}