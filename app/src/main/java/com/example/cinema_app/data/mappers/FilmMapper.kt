package com.example.cinema_app.data.mappers

import com.example.cinema_app.data.dbentity.MovieCached
import com.example.cinema_app.data.entity.Film
import com.example.cinema_app.domain.entity.FilmDTO
import com.example.cinema_app.domain.usecase.GetAverageFilmRatingsUseCase
import javax.inject.Inject

class FilmMapper @Inject constructor(
    private val getAverageFilmRatingsUseCase: GetAverageFilmRatingsUseCase
) {
    fun map(data: Film, userRating: Int?): FilmDTO {
        return FilmDTO(
            country = data.country,
            genres = data.genres,
            id = data.id,
            name = data.name,
            poster = data.poster,
            reviews = data.reviews,
            year = data.year,
            filmRating = getAverageFilmRatingsUseCase.execute(data.reviews, 1),
            userReview = userRating
        )
    }

    fun mapToCached(data: Film, userRating: Int?): MovieCached {
        return MovieCached(
            filmName = data.name,
            userRating = userRating,
            filmCountry = data.country,
            filmYear = data.year,
            filmGenres = extractGenresFromFilm(data)
        )
    }


    private fun extractGenresFromFilm(film: Film): List<String> {
        return film.genres.map { it.name }
    }
}



