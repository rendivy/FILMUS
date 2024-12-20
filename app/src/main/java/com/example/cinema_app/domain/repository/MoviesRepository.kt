package com.example.cinema_app.domain.repository

import com.example.cinema_app.data.entity.FilmDetailsApi
import com.example.cinema_app.data.entity.FilmShort
import com.example.cinema_app.ui.state.ReviewContent

interface MoviesRepository {
    suspend fun getMovies(): List<FilmShort>

    suspend fun addReview(movieId: String, reviewText: String, rating: Int, isAnonymous: Boolean)

    suspend fun getMovieDetails(movieId: String): FilmDetailsApi

    suspend fun deleteReview(movieId: String, reviewId: String)

    suspend fun editReview(movieId: String, reviewId: String, review: ReviewContent)
}