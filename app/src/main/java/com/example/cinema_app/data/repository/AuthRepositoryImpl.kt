package com.example.cinema_app.data.repository

import com.example.cinema_app.data.api.auth.LoginApi
import com.example.cinema_app.data.api.auth.RegistrationApi
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.storage.TokenLocalStorage
import com.example.cinema_app.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

class AuthRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val tokenLocalStorage: TokenLocalStorage,
) : AuthRepository {

    override suspend fun registerUser(
        userName: String,
        email: String,
        birthDate: String,
        password: String,
        name: String,
        gender: Int,
    ) {
        val registrationApi = createRegistrationApi(
            userName = userName,
            name = email,
            password = birthDate,
            email = password,
            birthDate = name,
            gender = gender,
        )
        movieApiService.register(registrationApi = registrationApi).run {
            tokenLocalStorage.saveToken(token)
        }
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return tokenLocalStorage.getToken().isNotBlank()
    }

    override suspend fun loginUser(login: String, password: String) {
        val loginBody = LoginApi(login = login, password = password)
        val tokenResponse = movieApiService.login(loginApi = loginBody).token
        tokenLocalStorage.saveToken(tokenResponse)
    }

    private fun createRegistrationApi(
        userName: String,
        name: String,
        password: String,
        email: String,
        birthDate: String,
        gender: Int
    ): RegistrationApi {
        return RegistrationApi(
            userName = userName,
            name = name,
            password = password,
            email = email,
            birthDate = birthDate,
            gender = gender
        )
    }
}