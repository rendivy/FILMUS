package com.example.cinema_app.data.repository

import com.example.cinema_app.data.entity.ProfileCredentials
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.storage.TokenLocalStorage
import com.example.cinema_app.domain.repository.ProfileRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val tokenManager: TokenLocalStorage,
    private val movieApiService: MovieApiService
) : ProfileRepository {

    override suspend fun getProfileData(): ProfileCredentials {
        val token = tokenManager.getToken()
        return movieApiService.getUserData(token = "Bearer $token")
    }

    override suspend fun logout() {
        val token = tokenManager.getToken()
        movieApiService.logout(
            token = "Bearer $token"
        )
    }


    override suspend fun updateProfileData(profileCredentials: ProfileCredentials) {
        val token = tokenManager.getToken()
        movieApiService.updateUserData(
            token = "Bearer $token",
            newCredentials = profileCredentials
        )
    }
}