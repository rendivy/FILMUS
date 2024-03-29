package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.entity.RegistrationBody
import com.example.cinema_app.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend fun invoke(registrationBody: RegistrationBody) {
        repository.registerUser(
            RegistrationBody(
                userName = registrationBody.userName,
                name = registrationBody.name,
                password = registrationBody.password,
                email = registrationBody.email,
                birthDate = registrationBody.birthDate,
                gender = registrationBody.gender
            )
        )
    }

}