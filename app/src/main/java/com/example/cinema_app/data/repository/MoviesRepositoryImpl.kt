package com.example.cinema_app.data.repository

import com.example.cinema_app.data.entity.AddReviewBody
import com.example.cinema_app.data.entity.FilmDetails
import com.example.cinema_app.data.entity.Movie
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.storage.TokenLocalStorage
import com.example.cinema_app.domain.repository.MoviesRepository
import com.example.cinema_app.domain.usecase.ReviewMapperUseCase
import com.example.cinema_app.ui.state.ReviewContent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val moviesApiService: MovieApiService,
    private val tokenManager: TokenLocalStorage,
    private val reviewMapperUseCase: ReviewMapperUseCase
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

    override suspend fun deleteReview(movieId: String, reviewId: String) {
        val token = tokenManager.getToken()
        moviesApiService.deleteReviewMovie(
            token = "Bearer $token",
            movieId = movieId,
            reviewId = reviewId
        )
    }

    override suspend fun editReview(movieId: String, reviewId: String, review: ReviewContent) {
        val token = tokenManager.getToken()
        val remoteReviewBody = reviewMapperUseCase.map(review)
        moviesApiService.editReviewMovie(
            token = "Bearer $token",
            movieId = movieId,
            reviewId = reviewId,
            addReviewBody =  remoteReviewBody
        )
    }
}