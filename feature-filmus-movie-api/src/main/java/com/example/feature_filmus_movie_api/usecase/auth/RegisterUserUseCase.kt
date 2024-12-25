package com.example.feature_filmus_movie_api.usecase.auth

import com.example.feature_filmus_movie_api.repository.AuthRepository

class RegisterUserUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(
        userName: String,
        email: String,
        birthDate: String,
        password: String,
        name: String,
        gender: Int,
    ) {
        repository.registerUser(
            userName = userName,
            email = email,
            birthDate = birthDate,
            password = password,
            name = name,
            gender = gender
        )
    }
}
