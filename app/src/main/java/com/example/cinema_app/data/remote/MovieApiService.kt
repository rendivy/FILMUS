package com.example.cinema_app.data.remote

import com.example.cinema_app.common.Constants
import com.example.cinema_app.data.entity.RegistrationBody
import com.example.cinema_app.data.entity.Token
import retrofit2.http.Body
import retrofit2.http.POST


interface MovieApiService {
    @POST(Constants.REGISTRATION_URL)
    suspend fun register(@Body registrationBody: RegistrationBody): Token

}