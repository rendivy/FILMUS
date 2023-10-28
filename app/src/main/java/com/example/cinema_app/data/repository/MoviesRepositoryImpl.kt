package com.example.cinema_app.data.repository

import com.example.cinema_app.data.entity.Movie
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.domain.repository.MoviesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(private val moviesApiService: MovieApiService) : MoviesRepository {
    override suspend fun getMovies(): Movie {
        return moviesApiService.getMovies(1)
    }
}