package com.example.cinema_app.domain.repository

import com.example.cinema_app.data.entity.RegistrationRequest
import com.example.cinema_app.data.entity.TokenBody
import com.example.cinema_app.data.remote.MovieApiService
import javax.inject.Inject

class AuthRepository @Inject constructor(private val movieApiService: MovieApiService) {
    suspend fun registerUser(registrationRequest: RegistrationRequest): TokenBody {
        return movieApiService.register(registrationRequest = registrationRequest)
    }
}