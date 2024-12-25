package com.example.feature_filmus_movie_api.usecase.auth

import com.example.feature_filmus_movie_api.repository.AuthRepository

class LoginUserUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(login: String, password: String) {
        repository.loginUser(login = login, password = password)
    }
}


