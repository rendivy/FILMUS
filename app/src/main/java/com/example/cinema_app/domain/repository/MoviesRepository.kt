package com.example.cinema_app.domain.repository

import com.example.cinema_app.data.entity.FilmDetails
import com.example.cinema_app.data.entity.Movie

interface MoviesRepository {
    suspend fun getMovies(): Movie

    suspend fun getMovieDetails(movieId: String) : FilmDetails

}