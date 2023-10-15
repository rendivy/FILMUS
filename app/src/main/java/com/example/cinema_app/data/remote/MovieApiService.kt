package com.example.cinema_app.data.remote

import com.example.cinema_app.data.entity.RegistrationRequest
import com.example.cinema_app.data.entity.TokenBody
import retrofit2.http.Body
import retrofit2.http.POST


interface MovieApiService {
    @POST("/api/account/register")
    suspend fun register(@Body registrationRequest: RegistrationRequest): TokenBody
}