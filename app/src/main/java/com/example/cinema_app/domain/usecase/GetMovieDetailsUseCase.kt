package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.mappers.DetailsMapper
import com.example.cinema_app.data.repository.MoviesRepositoryImpl
import com.example.cinema_app.domain.entity.DetailsDTO
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepositoryImpl,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val detailsMapper: DetailsMapper
) {

    suspend fun execute(movieId: String): DetailsDTO {
        val movieDetails = moviesRepository.getMovieDetails(movieId)
        val userReviewX = getUserProfileUseCase.getUserReview(movieDetails.reviews)
        return detailsMapper.map(movieDetails, userReviewX)
    }

}