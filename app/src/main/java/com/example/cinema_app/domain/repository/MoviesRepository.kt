package com.example.cinema_app.domain.repository

import com.example.cinema_app.data.entity.Movie

interface MoviesRepository {
    suspend fun getMovies(): Movie
}