package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.repository.MoviesRepositoryImpl
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val moviesRepository: MoviesRepositoryImpl) {
    suspend fun execute(movieId: String) = moviesRepository.getMovieDetails(movieId)
}