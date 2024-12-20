package com.example.cinema_app.domain.usecase.auth

import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val repository: com.example.cinema_app.domain.repository.AuthRepository) {

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