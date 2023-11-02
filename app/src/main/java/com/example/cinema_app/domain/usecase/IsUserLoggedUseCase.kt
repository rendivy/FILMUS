package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.repository.AuthRepositoryImpl
import javax.inject.Inject

class IsUserLoggedUseCase @Inject constructor(private val repository: AuthRepositoryImpl) {
    suspend fun execute(): Boolean = repository.isUserLoggedIn()
}