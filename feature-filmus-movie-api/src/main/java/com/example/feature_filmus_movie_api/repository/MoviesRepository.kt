package com.example.feature_filmus_movie_api.repository

import com.example.feature_filmus_movie_api.entities.movie.MovieDetails
import com.example.feature_filmus_movie_api.entities.movie.MovieWithRatings


interface MoviesRepository {

    suspend fun getAllFilmsFromCache(): List<MovieWithRatings>

    suspend fun addReview(movieId: String, reviewText: String, rating: Int, isAnonymous: Boolean)

    suspend fun getMovieDetails(movieId: String): MovieDetails

    suspend fun deleteReview(movieId: String, reviewId: String)

    suspend fun editReview(
        movieId: String,
        reviewId: String,
        reviewText: String,
        rating: Int,
        isAnonymous: Boolean,
    )
}