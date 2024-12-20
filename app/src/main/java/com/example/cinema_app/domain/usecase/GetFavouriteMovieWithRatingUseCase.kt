package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.movie.mappers.toDomain
import com.example.cinema_app.database.MovieDataBase
import com.example.cinema_app.domain.repository.FavouriteMovieRepository
import com.example.cinema_app.presentation.entities.FavouriteMovieUi
import com.example.cinema_app.presentation.mappers.PresentationFilmMapper
import javax.inject.Inject

class GetFavouriteMovieWithRatingUseCase @Inject constructor(
    private val favouriteMovieRepository: FavouriteMovieRepository,
    private val presentationFilmMapper: PresentationFilmMapper,
    private val database: MovieDataBase,
) {
    suspend fun execute(): List<FavouriteMovieUi> {
        val movieList = favouriteMovieRepository.getFavouriteMovie().movies
        val filmList: MutableList<FavouriteMovieUi> = mutableListOf()
        movieList.forEach {
            val userReview = database.ratingDao().getUserRating(it.id)
            val mappedFilm = presentationFilmMapper.map(it.toDomain(), userReview?.userRating)
            filmList.add(mappedFilm)
        }
        return filmList
    }
}
