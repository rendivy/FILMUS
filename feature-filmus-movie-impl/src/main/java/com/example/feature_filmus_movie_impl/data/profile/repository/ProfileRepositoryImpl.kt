package com.example.feature_filmus_movie_impl.data.profile.repository

import com.example.feature_filmus_movie_api.entities.profile.Profile
import com.example.feature_filmus_movie_api.repository.ProfileRepository
import com.example.feature_filmus_movie_impl.data.profile.mapper.toApi
import com.example.feature_filmus_movie_impl.data.profile.mapper.toDomain
import com.example.feature_filmus_movie_impl.data.api.MovieApiService
import com.example.feature_filmus_movie_impl.data.storage.TokenLocalStorage
import com.example.feature_filmus_movie_impl.database.dao.RatingDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val tokenLocalStorage: TokenLocalStorage,
    private val movieApiService: MovieApiService,
    private val ratingDao: RatingDao,
) : ProfileRepository {

    override suspend fun getProfileData(): Profile {
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