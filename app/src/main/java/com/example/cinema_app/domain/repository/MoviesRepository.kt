package com.example.cinema_app.domain.repository

import com.example.cinema_app.data.entity.PaginatorMovieList
import com.example.cinema_app.data.movie.entities.MovieDetailsApi
import com.example.cinema_app.domain.entities.movie.MovieWithRatings


interface MoviesRepository {

    suspend fun getAllFilmsFromCache(): List<MovieWithRatings>

    suspend fun getMovies(): PaginatorMovieList

    suspend fun addReview(movieId: String, reviewText: String, rating: Int, isAnonymous: Boolean)

    suspend fun getMovieDetails(movieId: String): MovieDetailsApi

    suspend fun deleteReview(movieId: String, reviewId: String)

    suspend fun editReview(
        movieId: String,
        reviewId: String,
        reviewText: String,
        rating: Int,
        isAnonymous: Boolean,
    )
}