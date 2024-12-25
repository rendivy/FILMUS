package com.example.feature_filmus_movie_api.usecase

import com.example.feature_filmus_movie_api.entities.movie.MovieWithRatings
import com.example.feature_filmus_movie_api.repository.MoviesRepository

class GetAllCachedFilmsUseCase(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke() : List<MovieWithRatings> {
        return moviesRepository.getAllFilmsFromCache()
    }
}
