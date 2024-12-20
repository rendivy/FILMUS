package com.example.cinema_app.data.movie.repository

import com.example.cinema_app.data.entity.PaginatorMovieList
import com.example.cinema_app.data.mappers.movie.toDomain
import com.example.cinema_app.data.movie.entities.MovieDetailsApi
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.review.entities.AddReviewApi
import com.example.cinema_app.data.storage.TokenLocalStorage
import com.example.cinema_app.database.dao.MovieDao
import com.example.cinema_app.database.dao.RatingDao
import com.example.cinema_app.database.entities.RatingsDb
import com.example.cinema_app.domain.entities.movie.MovieWithRatings
import com.example.cinema_app.domain.repository.MoviesRepository
import javax.inject.Inject

internal class MoviesRepositoryImpl @Inject constructor(
    private val moviesApiService: MovieApiService,
    private val tokenLocalStorage: TokenLocalStorage,
    private val ratingsDao: RatingDao,
    private val movieDao: MovieDao,
) : MoviesRepository {

    override suspend fun getAllFilmsFromCache(): List<MovieWithRatings> {
        return movieDao.getAllMovies().map { it.toDomain() }
    }

    override suspend fun getMovies(): PaginatorMovieList {
        return moviesApiService.getMovies(1)
    }

    override suspend fun addReview(
        movieId: String,
        reviewText: String,
        rating: Int,
        isAnonymous: Boolean
    ) {
        val ratingsDb = RatingsDb(filmId = movieId, userRating = rating)
        ratingsDao.updateUserRating(ratingsDb)
        val token = tokenLocalStorage.getToken()
        val addReviewApi = AddReviewApi(
            reviewText = reviewText,
            rating = rating,
            isAnonymous = isAnonymous
        )
        moviesApiService.addReviewMovie(
            token = "Bearer $token",
            movieId = movieId,
            addReviewApi = addReviewApi,
        )
    }

    override suspend fun getMovieDetails(movieId: String): MovieDetailsApi {
        return moviesApiService.getMovieDetails(movieId)
    }

    override suspend fun deleteReview(movieId: String, reviewId: String) {
        val token = tokenLocalStorage.getToken()
        val ratingsDb = RatingsDb(filmId = movieId, userRating = null)
        ratingsDao.updateUserRating(ratingsDb)
        moviesApiService.deleteReviewMovie(
            token = "Bearer $token",
            movieId = movieId,
            reviewId = reviewId
        )
    }

    override suspend fun editReview(
        movieId: String,
        reviewId: String,
        reviewText: String,
        rating: Int,
        isAnonymous: Boolean
    ) {
        val ratingsDb = RatingsDb(filmId = movieId, userRating = rating)
        ratingsDao.updateUserRating(ratingsDb)

        val token = tokenLocalStorage.getToken()
        val remoteReviewBody = AddReviewApi(
            reviewText = reviewText,
            rating = rating,
            isAnonymous = isAnonymous,
        )
        moviesApiService.editReviewMovie(
            token = "Bearer $token",
            movieId = movieId,
            reviewId = reviewId,
            addReviewApi = remoteReviewBody
        )
    }
}