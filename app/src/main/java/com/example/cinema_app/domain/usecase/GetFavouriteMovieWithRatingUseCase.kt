package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.repository.FavouriteMovieRepositoryImpl
import com.example.cinema_app.data.repository.MoviesRepositoryImpl
import com.example.cinema_app.domain.entity.FavouriteDTO
import com.example.cinema_app.presentation.mappers.PresentationFilmMapper
import javax.inject.Inject

class GetFavouriteMovieWithRatingUseCase @Inject constructor(
    private val favouriteMovieRepository: FavouriteMovieRepositoryImpl,
    private val presentationFilmMapper: PresentationFilmMapper,
    private val movieRepositoryImpl: MoviesRepositoryImpl,
    private val getUserProfileUseCase: GetUserProfileUseCase
) {
    suspend fun execute(): List<FavouriteDTO> {
        val movie = favouriteMovieRepository.getFavouriteMovie().movies
        val filmList: MutableList<FavouriteDTO> = mutableListOf()
        movie.forEach {
            val filmDetails = movieRepositoryImpl.getMovieDetails(it.id)
            val userReview = getUserProfileUseCase.getUserReview(filmDetails.reviews)
            val mappedFilm = presentationFilmMapper.map(it, userReview?.rating)
            filmList.add(mappedFilm)
        }
        return filmList
    }
}
