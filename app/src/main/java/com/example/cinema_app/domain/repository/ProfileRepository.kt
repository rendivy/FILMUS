package com.example.cinema_app.domain.repository

import com.example.cinema_app.data.entity.ProfileCredentials

interface ProfileRepository {

    suspend fun getProfileData() : ProfileCredentials

    suspend fun logout()

    suspend fun updateProfileData(profileCredentials: ProfileCredentials)
}