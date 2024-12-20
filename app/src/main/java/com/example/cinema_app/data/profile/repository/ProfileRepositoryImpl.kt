package com.example.cinema_app.data.profile.repository

import com.example.cinema_app.data.profile.mapper.toApi
import com.example.cinema_app.data.profile.mapper.toDomain
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.storage.TokenLocalStorage
import com.example.cinema_app.database.dao.RatingDao
import com.example.cinema_app.domain.entities.profile.Profile
import com.example.cinema_app.domain.repository.ProfileRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val tokenLocalStorage: TokenLocalStorage,
    private val movieApiService: MovieApiService,
    private val ratingDao: RatingDao,
) : ProfileRepository {

    override suspend fun getProfileData(): com.example.cinema_app.domain.entities.profile.Profile {
        val token = tokenLocalStorage.getToken()
        return movieApiService.getUserData(token = "Bearer $token").toDomain()
    }

    override suspend fun logout() {
        val token = tokenLocalStorage.getToken()
        ratingDao.deleteAllUserRatings()
        movieApiService.logout(token = "Bearer $token")
        tokenLocalStorage.deleteToken()
    }


    override suspend fun updateProfileData(profile: Profile) {
        val token = tokenLocalStorage.getToken()
        movieApiService.updateUserData(
            token = "Bearer $token",
            newCredentials = profile.toApi(),
        )
    }
}