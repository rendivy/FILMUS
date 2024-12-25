package com.example.feature_filmus_movie_impl.data.movie.repository

import com.example.feature_filmus_movie_api.entities.movie.MovieDetails
import com.example.feature_filmus_movie_api.entities.movie.MovieWithRatings
import com.example.feature_filmus_movie_api.repository.MoviesRepository
import com.example.feature_filmus_movie_impl.data.api.MovieApiService
import com.example.feature_filmus_movie_impl.data.mappers.movie.toDomain
import com.example.feature_filmus_movie_impl.data.review.entities.AddReviewApi
import com.example.feature_filmus_movie_impl.data.review.mapper.toDomain
import com.example.feature_filmus_movie_impl.data.storage.TokenLocalStorage
import com.example.feature_filmus_movie_impl.database.dao.MovieDao
import com.example.feature_filmus_movie_impl.database.dao.RatingDao
import com.example.feature_filmus_movie_impl.database.entities.RatingsDb
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

    override suspend fun getMovieDetails(movieId: String): MovieDetails {
        return moviesApiService.getMovieDetails(movieId).toDomain()
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

