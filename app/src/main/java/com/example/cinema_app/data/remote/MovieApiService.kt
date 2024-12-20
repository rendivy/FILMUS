package com.example.cinema_app.data.remote


import com.example.cinema_app.common.NetworkConstant
import com.example.cinema_app.data.api.ProfileApi
import com.example.cinema_app.data.api.auth.LoginApi
import com.example.cinema_app.data.api.auth.RegistrationApi
import com.example.cinema_app.data.api.review.AddReviewApi
import com.example.cinema_app.data.entity.FilmDetailsApi
import com.example.cinema_app.data.entity.FilmShort
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
    suspend fun register(@Body registrationApi: RegistrationApi): Token

    @GET(NetworkConstant.FAVORITE_MOVIES_URL)
    suspend fun getFavoriteMovies(@Header("Authorization") token: String): List<FilmShort>

    @POST(NetworkConstant.LOGIN_URL)
    suspend fun login(@Body loginApi: LoginApi): Token

    @GET(NetworkConstant.MOVIES_URL)
    suspend fun getMovies(@Path("page") page: Int): List<FilmShort>

    @POST("${NetworkConstant.REVIEW_URL}add")
    suspend fun addReviewMovie(
        @Header("Authorization") token: String,
        @Path("movieId") movieId: String,
        @Body addReviewApi: AddReviewApi,
    )

    @POST("${NetworkConstant.FAVORITE_MOVIES_URL}/{id}/add")
    suspend fun addFavouriteMovie(
        @Header("Authorization") token: String,
        @Path("id") movieId: String
    )

    @DELETE("${NetworkConstant.FAVORITE_MOVIES_URL}/{id}/delete")
    suspend fun deleteFavouriteMovie(
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
        @Body addReviewApi: AddReviewApi
    )

    @DELETE("${NetworkConstant.REVIEW_URL}{id}/delete")
    suspend fun deleteReviewMovie(
        @Header("Authorization") token: String,
        @Path("movieId") movieId: String,
        @Path("id") reviewId: String
    )

    @GET(NetworkConstant.PROFILE_URL)
    suspend fun getUserData(@Header("Authorization") token: String): ProfileApi


    @GET(NetworkConstant.MOVIE_DETAILS_URL)
    suspend fun getMovieDetails(@Path("id") id: String): FilmDetailsApi


    @PUT(NetworkConstant.PROFILE_URL)
    suspend fun updateUserData(
        @Header("Authorization") token: String,
        @Body newCredentials: ProfileApi
    )

}


