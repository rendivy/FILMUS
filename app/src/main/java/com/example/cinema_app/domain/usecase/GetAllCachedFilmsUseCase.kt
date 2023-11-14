package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.database.MovieDataBase
import javax.inject.Inject

class GetAllCachedFilmsUseCase @Inject constructor(private val movieDataBase: MovieDataBase) {
    fun execute() = movieDataBase.movieDao().getAllMovies()
}