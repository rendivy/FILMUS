package com.example.cinema_app.domain.usecase

import com.example.cinema_app.domain.repository.AuthRepository
import javax.inject.Inject

class IsUserLoggedUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend fun execute(): Boolean = repository.isUserLoggedIn()
}