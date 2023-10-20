package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.entity.AuthenticationBody
import com.example.cinema_app.data.entity.RegistrationBody
import com.example.cinema_app.domain.repository.AuthRepository
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend fun registerUser(registrationBody: RegistrationBody) {
        repository.registerUser(
            RegistrationBody(
                userName = registrationBody.userName,
                name = registrationBody.name,
                password = registrationBody.password,
                email = registrationBody.email,
            )
        )
    }

    suspend fun loginUser(loginBody: AuthenticationBody) {
        repository.loginUser(
            AuthenticationBody(
                username = loginBody.username,
                password = loginBody.password,
            )
        )
    }

}