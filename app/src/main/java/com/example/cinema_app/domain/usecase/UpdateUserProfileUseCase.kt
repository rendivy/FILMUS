package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.entity.ProfileCredentials
import com.example.cinema_app.domain.repository.ProfileRepository
import javax.inject.Inject

class UpdateUserProfileUseCase @Inject constructor(private val repository: ProfileRepository) {
    suspend fun execute(profileCredentials: ProfileCredentials) {
        repository.updateProfileData(profileCredentials)
    }
}