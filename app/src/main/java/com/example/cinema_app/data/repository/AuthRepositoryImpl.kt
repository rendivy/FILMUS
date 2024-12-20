package com.example.cinema_app.data.repository

import com.example.cinema_app.common.Constants
import com.example.cinema_app.data.api.auth.LoginApi
import com.example.cinema_app.data.api.auth.RegistrationApi
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.storage.TokenLocalStorage
import com.example.cinema_app.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val tokenLocalStorage: TokenLocalStorage,
) : AuthRepository {

    override suspend fun registerUser(registrationApi: RegistrationApi) {
        tokenLocalStorage.saveToken(
            movieApiService.register(registrationApi = registrationApi).token
        )
    }


    override suspend fun isUserLoggedIn(): Boolean {
        return tokenLocalStorage.getToken() != Constants.EMPTY_STRING
    }

    override suspend fun loginUser(loginApi: LoginApi) {
        tokenLocalStorage.saveToken(movieApiService.login(loginApi = loginApi).token)
    }

}