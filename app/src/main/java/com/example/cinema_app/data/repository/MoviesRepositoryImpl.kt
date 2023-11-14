package com.example.cinema_app.data.repository

import com.example.cinema_app.data.database.MovieDataBase
import com.example.cinema_app.data.dbentity.UserRating
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
    private val tokenLocalStorage: TokenLocalStorage,
    private val reviewMapperUseCase: ReviewMapperUseCase,
    private val dataBase: MovieDataBase
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
        val dao = dataBase.userDao()
        val userRating = UserRating(
            filmId = movieId,
            userRating = rating
        )
        dao.updateUserRating(
            userRating = userRating,
        )
        val token = tokenLocalStorage.getToken()
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
        val token = tokenLocalStorage.getToken()
        val dao = dataBase.userDao()
        val userRating = UserRating(
            filmId = movieId,
            userRating = null
        )
        dao.updateUserRating(
            userRating = userRating,
        )
        moviesApiService.deleteReviewMovie(
            token = "Bearer $token",
            movieId = movieId,
            reviewId = reviewId
        )
    }

    override suspend fun editReview(movieId: String, reviewId: String, review: ReviewContent) {
        val userRating = UserRating(
            filmId = movieId,
            userRating = review.rating.toInt()
        )
        val dao = dataBase.userDao()
        dao.updateUserRating(
            userRating = userRating,
        )
        val token = tokenLocalStorage.getToken()
        val remoteReviewBody = reviewMapperUseCase.execute(review)
        moviesApiService.editReviewMovie(
            token = "Bearer $token",
            movieId = movieId,
            reviewId = reviewId,
            addReviewBody = remoteReviewBody
        )
    }
}