package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.entity.ProfileCredentials
import com.example.cinema_app.data.repository.ProfileRepositoryImpl
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(private val profileRepository: ProfileRepositoryImpl) {
    suspend fun execute(): ProfileCredentials {
        return profileRepository.getProfileData()
    }
}