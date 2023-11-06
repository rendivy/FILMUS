package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.mappers.FilmMapper
import com.example.cinema_app.data.repository.MoviesRepositoryImpl
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepositoryImpl,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val filmMapper: FilmMapper,
    private val getAverageFilmRatingsUseCase: GetAverageFilmRatingsUseCase
) {
    suspend fun execute(movieId: String) = moviesRepository.getMovieDetails(movieId)
}