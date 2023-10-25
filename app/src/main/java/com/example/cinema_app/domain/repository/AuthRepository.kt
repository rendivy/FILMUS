package com.example.cinema_app.domain.repository

import com.example.cinema_app.data.entity.LoginBody
import com.example.cinema_app.data.entity.RegistrationBody


interface AuthRepository {

    suspend fun registerUser(registrationBody: RegistrationBody)

    suspend fun loginUser(loginBody: LoginBody)
}