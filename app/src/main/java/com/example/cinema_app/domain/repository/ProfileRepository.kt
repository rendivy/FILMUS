package com.example.cinema_app.domain.repository

import com.example.cinema_app.data.api.ProfileApi

interface ProfileRepository {

    suspend fun getProfileData() : ProfileApi

    suspend fun logout()

    suspend fun updateProfileData(profileApi: ProfileApi)
}