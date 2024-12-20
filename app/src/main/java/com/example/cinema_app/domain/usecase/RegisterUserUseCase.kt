package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.api.auth.RegistrationApi
import com.example.cinema_app.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend fun invoke(registrationApi: RegistrationApi) {
        repository.registerUser(
            RegistrationApi(
                userName = registrationApi.userName,
                name = registrationApi.name,
                password = registrationApi.password,
                email = registrationApi.email,
                birthDate = registrationApi.birthDate,
                gender = registrationApi.gender
            )
        )
    }

}