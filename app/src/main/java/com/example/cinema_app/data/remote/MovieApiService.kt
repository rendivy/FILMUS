package com.example.cinema_app.data.remote

import com.example.cinema_app.common.NetworkConstant
import com.example.cinema_app.data.entity.LoginBody
import com.example.cinema_app.data.entity.RegistrationBody
import com.example.cinema_app.data.entity.Token
import retrofit2.http.Body
import retrofit2.http.POST


interface MovieApiService {
    @POST(NetworkConstant.REGISTRATION_URL)
    suspend fun register(@Body registrationBody: RegistrationBody): Token


    @POST(NetworkConstant.LOGIN_URL)
    suspend fun login(@Body loginBody: LoginBody): Token
}


