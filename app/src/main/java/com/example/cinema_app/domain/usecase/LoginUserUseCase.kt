package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.entity.LoginBody
import com.example.cinema_app.data.repository.AuthRepositoryImpl
import com.example.cinema_app.domain.repository.AuthRepository
import com.example.cinema_app.ui.state.LoginContent
import javax.inject.Inject


class LoginUserUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend fun invoke(loginContent: LoginContent) {
        repository.loginUser(
            LoginBody(
                username = loginContent.username,
                password = loginContent.password
            )
        )
    }
}