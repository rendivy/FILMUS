package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.api.ProfileApi
import com.example.cinema_app.domain.repository.ProfileRepository
import javax.inject.Inject

class UpdateUserProfileUseCase @Inject constructor(private val repository: ProfileRepository) {
    suspend fun execute(profileApi: ProfileApi) {
        repository.updateProfileData(profileApi)
    }
}