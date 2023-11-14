package com.example.cinema_app.data.repository

import com.example.cinema_app.data.database.MovieDataBase
import com.example.cinema_app.data.entity.ProfileCredentials
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.storage.TokenLocalStorage
import com.example.cinema_app.domain.repository.ProfileRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val tokenLocalStorage: TokenLocalStorage,
    private val movieApiService: MovieApiService,
    private val dataBase: MovieDataBase
) : ProfileRepository {

    override suspend fun getProfileData(): ProfileCredentials {
        val token = tokenLocalStorage.getToken()
        return movieApiService.getUserData(token = "Bearer $token")
    }

    override suspend fun logout() {
        val dao = dataBase.userDao()
        val token = tokenLocalStorage.getToken()
        dao.deleteAllUserRatings()
        movieApiService.logout(
            token = "Bearer $token"
        )
        tokenLocalStorage.deleteToken()
    }


    override suspend fun updateProfileData(profileCredentials: ProfileCredentials) {
        val token = tokenLocalStorage.getToken()
        movieApiService.updateUserData(
            token = "Bearer $token",
            newCredentials = profileCredentials
        )
    }
}