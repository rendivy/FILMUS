package com.example.cinema_app.domain.repository

import com.example.cinema_app.domain.entities.profile.Profile

interface ProfileRepository {

    suspend fun getProfileData() : Profile

    suspend fun logout()

    suspend fun updateProfileData(profile: Profile)
}