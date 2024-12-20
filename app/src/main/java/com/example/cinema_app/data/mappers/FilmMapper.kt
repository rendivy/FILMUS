package com.example.cinema_app.data.mappers

import com.example.cinema_app.data.dbentity.MovieDb
import com.example.cinema_app.data.entity.FilmShort
import com.example.cinema_app.domain.entity.FilmItemUi
import com.example.cinema_app.domain.usecase.GetAverageFilmRatingsUseCase
import javax.inject.Inject

class FilmMapper @Inject constructor(
    private val getAverageFilmRatingsUseCase: GetAverageFilmRatingsUseCase
) {
    fun map(data: FilmShort, userRating: Int?): FilmItemUi {
        return FilmItemUi(
            country = data.country,
            genres = data.genres,
            id = data.id,
            name = data.name,
            poster = data.poster,
            reviewShortApis = data.reviewShortApis,
            year = data.year,
            filmRating = getAverageFilmRatingsUseCase.execute(data.reviewShortApis, 1),
            userReview = userRating
        )
    }

    fun mapToCached(data: FilmShort, userRating: Int?): MovieDb {
        return MovieDb(
            filmName = data.name,
            userRating = userRating,
            filmCountry = data.country,
            filmYear = data.year,
            filmGenres = extractGenresFromFilm(data)
        )
    }


    private fun extractGenresFromFilm(filmShort: FilmShort): List<String> {
        return filmShort.genres.map { it.name }
    }
}



