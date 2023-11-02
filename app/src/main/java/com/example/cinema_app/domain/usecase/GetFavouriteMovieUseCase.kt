package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.repository.FavouriteMovieRepositoryImpl
import javax.inject.Inject

class GetFavouriteMovieUseCase @Inject constructor(
    private val favouriteMovieRepository: FavouriteMovieRepositoryImpl
) {

    suspend fun execute() = favouriteMovieRepository.getFavouriteMovie()
}
