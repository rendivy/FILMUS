package com.example.cinema_app.domain.usecase.profile

import com.example.cinema_app.domain.repository.ProfileRepository
import javax.inject.Inject

class UpdateUserProfileUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend fun execute(profile: com.example.cinema_app.domain.entities.profile.Profile) {
        repository.updateProfileData(profile)
    }
}