package com.example.feature_filmus_movie_api.usecase.auth

import com.example.feature_filmus_movie_api.repository.AuthRepository

/**
 * @author y.yangel
 */
class IsUserLoggedUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(): Boolean {
        return repository.isUserLoggedIn()
    }
}