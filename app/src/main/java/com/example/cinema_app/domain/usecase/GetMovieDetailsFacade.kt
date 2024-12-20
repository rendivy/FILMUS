package com.example.cinema_app.domain.usecase

import androidx.compose.runtime.MutableState
import com.example.cinema_app.data.mappers.DetailsMapper
import com.example.cinema_app.data.review.entities.ReviewApi
import com.example.cinema_app.data.review.mapper.toDomain
import com.example.cinema_app.domain.entities.Review
import com.example.cinema_app.domain.usecase.profile.GetUserProfileUseCase
import com.example.cinema_app.presentation.entities.FavouriteMovieUi
import com.example.cinema_app.presentation.entities.MovieDetailsUi
import com.example.cinema_app.presentation.mappers.UserReviewMapper
import com.example.cinema_app.ui.state.ReviewContent
import javax.inject.Inject

class GetMovieDetailsFacade @Inject constructor(
    private val moviesRepository: com.example.cinema_app.domain.repository.MoviesRepository,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getFavouriteMovieUseCase: GetFavouriteMovieWithRatingUseCase,
    private val detailsMapper: DetailsMapper,
    private val reviewMapper: UserReviewMapper,
) {

    private fun isFilmInFavourite(
        favouriteMovies: List<FavouriteMovieUi>,
        movieId: String
    ): Boolean {
        favouriteMovies.forEach {
            if (it.id == movieId) {
                return true
            }
        }
        return false
    }

    suspend fun execute(movieId: String, userReview: MutableState<ReviewContent>): MovieDetailsUi {
        val movieDetails = moviesRepository.getMovieDetails(movieId)
        val favouriteMovies = getFavouriteMovieUseCase.execute()
        val filmInFavourite = isFilmInFavourite(favouriteMovies, movieId)
        val profile = getUserProfileUseCase()
        val userReviewX = getUserReview(profile.id, movieDetails.reviewApis)
        val averageFilmRating = averageFilmRating(movieDetails.reviewApis.map { it.toDomain() })
        if (userReviewX != null) {
            userReview.value = reviewMapper.map(userReviewX)
        }
        return detailsMapper.map(movieDetails, userReviewX, averageFilmRating, filmInFavourite)
    }

    private fun getUserReview(userId: String, filmReviewApis: List<ReviewApi>): Review? {
        return filmReviewApis.find { it.author?.userId == userId }?.toDomain()
    }

    private fun averageFilmRating(
        data: List<Review>,
        precision: Int = 1
    ): Double {
        if (data.isEmpty()) return 0.0
        var rating = 0
        data.forEach {
            rating += it.rating
        }
        val averageRating = rating.toDouble() / data.size
        val format = String.format("%.${precision}f", averageRating)
        return format.toDouble()
    }
}