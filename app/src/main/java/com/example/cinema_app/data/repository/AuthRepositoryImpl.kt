package com.example.cinema_app.data.repository


import android.util.Log
import com.example.cinema_app.data.entity.RegistrationBody
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.storage.TokenLocalStorage
import com.example.cinema_app.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val tokenLocalStorage: TokenLocalStorage
) : AuthRepository {

    override suspend fun registerUser(registrationBody: RegistrationBody) {
        tokenLocalStorage.saveToken(movieApiService.register(registrationBody = registrationBody).token)
        Log.d("TAG", "registerUser: ${tokenLocalStorage.getToken()}")
    }
}