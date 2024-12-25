package com.example.cinema_app.presentation.facade

import androidx.compose.runtime.MutableState
import com.example.cinema_app.presentation.DetailsMapper
import com.example.cinema_app.presentation.entities.FavouriteMovieUi
import com.example.cinema_app.presentation.entities.MovieDetailsUi
import com.example.cinema_app.presentation.mappers.UserReviewMapper
import com.example.cinema_app.ui.state.ReviewContent
import com.example.feature_filmus_movie_api.entities.Review
import com.example.feature_filmus_movie_api.repository.MoviesRepository
import com.example.feature_filmus_movie_api.usecase.profile.GetUserProfileUseCase
import javax.inject.Inject

class GetMovieDetailsFacade @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getFavouriteMovieUseCase: GetFavouriteMovieWithRatingFacade,
    private val detailsMapper: DetailsMapper,
    private val reviewMapper: UserReviewMapper,
) {

    suspend fun execute(movieId: String, userReview: MutableState<ReviewContent>): MovieDetailsUi {
        val movieDetails = moviesRepository.getMovieDetails(movieId)
        val favouriteMovies = getFavouriteMovieUseCase.execute()
        val filmInFavourite = isFilmInFavourite(favouriteMovies, movieId)
        val profile = getUserProfileUseCase()
        val userReviewX = getUserReview(profile.id, movieDetails.reviews)
        val averageFilmRating = averageFilmRating(movieDetails.reviews)
        if (userReviewX != null) {
            userReview.value = reviewMapper.map(userReviewX)
        }
        return detailsMapper.map(movieDetails, userReviewX, averageFilmRating, filmInFavourite)
    }

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

    private fun getUserReview(userId: String, filmReviewApis: List<Review>): Review? {
        return filmReviewApis.find { it.author?.userId == userId }
    }
}