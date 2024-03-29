package com.example.cinema_app.common

object NetworkConstant {
    const val BASE_URL = "https://react-midterm.kreosoft.space"
    const val REGISTRATION_URL = "/api/account/register"
    const val REVIEW_URL = "/api/movie/{movieId}/review/"
    const val MOVIES_URL = "/api/movies/{page}"
    const val LOGOUT_URL = "/api/account/logout"
    const val MOVIE_DETAILS_URL = "/api/movies/details/{id}"
    const val PROFILE_URL = "/api/account/profile"
    const val FAVORITE_MOVIES_URL = "/api/favorites"
    const val LOGIN_URL = "/api/account/login"
    const val CONNECT_TIMEOUT = 5L
    const val READ_TIMEOUT = 10L
    const val WRITE_TIMEOUT = 10L
}