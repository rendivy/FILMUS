package com.example.cinema_app.domain.usecase

import com.example.cinema_app.domain.entities.movie.MovieWithRatings
import com.example.cinema_app.domain.repository.MoviesRepository
import javax.inject.Inject

class GetAllCachedFilmsUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke() : List<MovieWithRatings> {
        return moviesRepository.getAllFilmsFromCache()
    }
}