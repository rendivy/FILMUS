package com.example.feature_filmus_movie_impl.data.repository

import com.example.feature_filmus_movie_api.repository.AuthRepository
import com.example.feature_filmus_movie_impl.data.api.MovieApiService
import com.example.feature_filmus_movie_impl.data.api.auth.LoginApi
import com.example.feature_filmus_movie_impl.data.api.auth.RegistrationApi
import com.example.feature_filmus_movie_impl.data.storage.TokenLocalStorage
import javax.inject.Inject

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
        val token = tokenLocalStorage.getToken().isNotBlank()
        return token
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