package com.example.cinema_app.domain.repository

import com.example.cinema_app.data.api.auth.LoginApi
import com.example.cinema_app.data.api.auth.RegistrationApi


interface AuthRepository {

    suspend fun registerUser(registrationApi: RegistrationApi)

    suspend fun isUserLoggedIn(): Boolean

    suspend fun loginUser(loginApi: LoginApi)
}