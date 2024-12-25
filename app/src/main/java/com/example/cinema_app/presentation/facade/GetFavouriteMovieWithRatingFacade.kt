package com.example.cinema_app.presentation.facade

import com.example.cinema_app.presentation.entities.FavouriteMovieUi
import com.example.cinema_app.presentation.mappers.PresentationFilmMapper
import com.example.feature_filmus_movie_api.repository.FavouriteMovieRepository
import com.example.feature_filmus_movie_impl.database.dao.RatingDao
import javax.inject.Inject

class GetFavouriteMovieWithRatingFacade @Inject constructor(
    private val favouriteMovieRepository: FavouriteMovieRepository,
    private val presentationFilmMapper: PresentationFilmMapper,
    private val ratingDao: RatingDao,
) {
    suspend fun execute(): List<FavouriteMovieUi> {
        val movieList = favouriteMovieRepository.getFavouriteMovie().movies
        val filmList: MutableList<FavouriteMovieUi> = mutableListOf()
        movieList.forEach {
            val userReview = ratingDao.getUserRating(it.id)
            val mappedFilm = presentationFilmMapper.map(it, userReview?.userRating)
            filmList.add(mappedFilm)
        }
        return filmList
    }
}