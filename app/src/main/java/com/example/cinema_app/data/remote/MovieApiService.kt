package com.example.cinema_app.data.remote


import com.example.cinema_app.common.NetworkConstant
import com.example.cinema_app.data.entity.AddReviewBody
import com.example.cinema_app.data.entity.FilmDetails
import com.example.cinema_app.data.entity.LoginBody
import com.example.cinema_app.data.entity.Movie
import com.example.cinema_app.data.entity.ProfileCredentials
import com.example.cinema_app.data.entity.RegistrationBody
import com.example.cinema_app.data.entity.Token
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface MovieApiService {
    @POST(NetworkConstant.REGISTRATION_URL)
    suspend fun register(@Body registrationBody: RegistrationBody): Token

    @GET(NetworkConstant.FAVORITE_MOVIES_URL)
    suspend fun getFavoriteMovies(@Header("Authorization") token: String): Movie

    @POST(NetworkConstant.LOGIN_URL)
    suspend fun login(@Body loginBody: LoginBody): Token

    @GET(NetworkConstant.MOVIES_URL)
    suspend fun getMovies(@Path("page") page: Int): Movie

    @POST("${NetworkConstant.REVIEW_URL}add")
    suspend fun addReviewMovie(
        @Header("Authorization") token: String,
        @Path("movieId") movieId: String,
        @Body addReviewBody: AddReviewBody
    )

    @POST("${NetworkConstant.FAVORITE_MOVIES_URL}/{id}/add")
    suspend fun addFavouriteMovie(
        @Header("Authorization") token: String,
        @Path("id") movieId: String
    )

    @POST(NetworkConstant.LOGOUT_URL)
    suspend fun logout(@Header("Authorization") token: String)

    @PUT("${NetworkConstant.REVIEW_URL}{id}/edit")
    suspend fun editReviewMovie(
        @Header("Authorization") token: String,
        @Path("movieId") movieId: String,
        @Path("id") reviewId: String,
        @Body addReviewBody: AddReviewBody
    )

    @DELETE("${NetworkConstant.REVIEW_URL}{id}/delete")
    suspend fun deleteReviewMovie(
        @Header("Authorization") token: String,
        @Path("movieId") movieId: String,
        @Path("id") reviewId: String
    )

    @GET(NetworkConstant.PROFILE_URL)
    suspend fun getUserData(@Header("Authorization") token: String): ProfileCredentials


    @GET(NetworkConstant.MOVIE_DETAILS_URL)
    suspend fun getMovieDetails(@Path("id") id: String): FilmDetails


    @PUT(NetworkConstant.PROFILE_URL)
    suspend fun updateUserData(
        @Header("Authorization") token: String,
        @Body newCredentials: ProfileCredentials
    )

}


