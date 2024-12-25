package com.example.feature_filmus_movie_api.repository

import com.example.feature_filmus_movie_api.entities.profile.Profile

interface ProfileRepository {

    suspend fun getProfileData() : Profile

    suspend fun logout()

    suspend fun updateProfileData(profile: Profile)
}