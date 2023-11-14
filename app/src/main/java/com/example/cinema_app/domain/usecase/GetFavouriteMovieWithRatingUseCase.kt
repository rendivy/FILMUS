package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.database.MovieDataBase
import com.example.cinema_app.domain.entity.FavouriteDTO
import com.example.cinema_app.domain.repository.FavouriteMovieRepository
import com.example.cinema_app.presentation.mappers.PresentationFilmMapper
import javax.inject.Inject

class GetFavouriteMovieWithRatingUseCase @Inject constructor(
    private val favouriteMovieRepository: FavouriteMovieRepository,
    private val presentationFilmMapper: PresentationFilmMapper,
    private val database: MovieDataBase,
) {
    suspend fun execute(): List<FavouriteDTO> {
        val movie = favouriteMovieRepository.getFavouriteMovie().movies
        val filmList: MutableList<FavouriteDTO> = mutableListOf()
        movie.forEach {
            val userReview = database.userDao().getUserRating(it.id)
            val mappedFilm = presentationFilmMapper.map(it, userReview?.userRating)
            filmList.add(mappedFilm)
        }
        return filmList
    }
}
