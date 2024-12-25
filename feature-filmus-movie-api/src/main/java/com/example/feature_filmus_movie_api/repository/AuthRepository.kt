package com.example.feature_filmus_movie_api.repository


interface AuthRepository {

    suspend fun registerUser(
        userName: String,
        email: String,
        birthDate: String,
        password: String,
        name: String,
        gender: Int,
    )

    suspend fun isUserLoggedIn(): Boolean

    suspend fun loginUser(login: String, password: String)
}