package com.example.cinema_app.domain.usecase

import com.example.cinema_app.domain.repository.FavouriteMovieRepository
import javax.inject.Inject

class DeleteFavouriteMovieUseCase @Inject constructor(private val favouriteMovieRepository: FavouriteMovieRepository)
{
    suspend fun execute(movieId: String){
        favouriteMovieRepository.deleteFavouriteMovie(movieId = movieId)
    }

}