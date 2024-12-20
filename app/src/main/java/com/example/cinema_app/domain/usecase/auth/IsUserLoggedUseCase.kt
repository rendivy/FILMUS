package com.example.cinema_app.domain.usecase.auth

import com.example.cinema_app.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * @author y.yangel
 */
class IsUserLoggedUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(): Boolean = repository.isUserLoggedIn()
}