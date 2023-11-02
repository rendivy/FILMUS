package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.repository.ProfileRepositoryImpl
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(private val profileRepositoryImpl: ProfileRepositoryImpl) {

        suspend fun execute(): String {
            return profileRepositoryImpl.getProfileData().id
        }
}