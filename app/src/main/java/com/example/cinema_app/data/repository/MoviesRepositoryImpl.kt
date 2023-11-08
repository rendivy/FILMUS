package com.example.cinema_app.data.repository

import com.example.cinema_app.data.entity.AddReviewBody
import com.example.cinema_app.data.entity.FilmDetails
import com.example.cinema_app.data.entity.Movie
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.storage.TokenLocalStorage
import com.example.cinema_app.domain.repository.MoviesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val moviesApiService: MovieApiService,
    private val tokenManager: TokenLocalStorage
) : MoviesRepository {

    override suspend fun getMovies(): Movie {
        return moviesApiService.getMovies(1)
    }

    override suspend fun addReview(
        movieId: String,
        reviewText: String,
        rating: Int,
        isAnonymous: Boolean
    ) {
        val token = tokenManager.getToken()
        val addReviewBody = AddReviewBody(
            reviewText = reviewText,
            rating = rating,
            isAnonymous = isAnonymous
        )
        moviesApiService.addReviewMovie(
            token = "Bearer $token",
            movieId = movieId,
            addReviewBody = addReviewBody
        )
    }

    override suspend fun getMovieDetails(movieId: String): FilmDetails {
        return moviesApiService.getMovieDetails(movieId)
    }
}