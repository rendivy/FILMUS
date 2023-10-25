package com.example.cinema_app.data.remote


import com.example.cinema_app.common.NetworkConstant
import com.example.cinema_app.data.entity.LoginBody
import com.example.cinema_app.data.entity.Movie
import com.example.cinema_app.data.entity.RegistrationBody
import com.example.cinema_app.data.entity.Token
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface MovieApiService {
    @POST(NetworkConstant.REGISTRATION_URL)
    suspend fun register(@Body registrationBody: RegistrationBody): Token

    @GET(NetworkConstant.FAVORITE_MOVIES_URL)
    suspend fun getFavoriteMovies(@Header("Authorization") token: String): Movie

    @POST(NetworkConstant.LOGIN_URL)
    suspend fun login(@Body loginBody: LoginBody): Token
}


