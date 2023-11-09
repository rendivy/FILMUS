package com.example.cinema_app.domain.usecase


import androidx.compose.runtime.MutableState
import com.example.cinema_app.data.entity.ReviewX
import com.example.cinema_app.data.mappers.DetailsMapper
import com.example.cinema_app.data.repository.MoviesRepositoryImpl
import com.example.cinema_app.domain.entity.DetailsDTO
import com.example.cinema_app.presentation.mappers.UserReviewMapper
import com.example.cinema_app.ui.state.ReviewContent
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepositoryImpl,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val detailsMapper: DetailsMapper,
    private val reviewMapper: UserReviewMapper
) {

    private fun averageFilmRating(data: List<ReviewX>, precision: Int): Double {
        if (data.isEmpty()) {
            return 0.0
        }

        var rating = 0
        data.forEach {
            rating += it.rating
        }

        val averageRating = rating.toDouble() / data.size
        val format = String.format("%.${precision}f", averageRating)

        return format.toDouble()
    }

    suspend fun execute(movieId: String, userReview: MutableState<ReviewContent>): DetailsDTO {
        val movieDetails = moviesRepository.getMovieDetails(movieId)
        val userReviewX = getUserProfileUseCase.getUserReview(movieDetails.reviews)
        val averageFilmRating = averageFilmRating(movieDetails.reviews, precision = 1)
        if (userReviewX != null){
            userReview.value = reviewMapper.map(userReviewX)
        }
        return detailsMapper.map(movieDetails, userReviewX, averageFilmRating)
    }

}