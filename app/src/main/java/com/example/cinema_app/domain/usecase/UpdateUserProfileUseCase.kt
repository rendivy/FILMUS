package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.entity.ProfileCredentials
import com.example.cinema_app.data.repository.ProfileRepositoryImpl
import javax.inject.Inject

class UpdateUserProfileUseCase @Inject constructor(private val repository: ProfileRepositoryImpl) {
    suspend fun execute(profileCredentials: ProfileCredentials) {
        repository.updateProfileData(profileCredentials)
    }
}