package com.example.cinema_app.domain.usecase.auth

import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val repository: com.example.cinema_app.domain.repository.AuthRepository) {

    suspend operator fun invoke(login: String, password: String) {
        repository.loginUser(login = login, password = password)
    }
}


