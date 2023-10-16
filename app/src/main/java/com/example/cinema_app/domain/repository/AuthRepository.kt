package com.example.cinema_app.domain.repository

import com.example.cinema_app.data.entity.RegistrationRequest
import com.example.cinema_app.data.entity.Token
import com.example.cinema_app.data.remote.MovieApiService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepository @Inject constructor(private val movieApiService: MovieApiService) {
    suspend fun registerUser(registrationRequest: RegistrationRequest): Token {
        return movieApiService.register(registrationRequest = registrationRequest)
    }
}