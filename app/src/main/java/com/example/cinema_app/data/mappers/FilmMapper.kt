package com.example.cinema_app.data.mappers

import com.example.cinema_app.data.entity.Film
import com.example.cinema_app.domain.entity.FilmDto
import com.example.cinema_app.domain.usecase.GetAverageFilmRatingsUseCase
import javax.inject.Inject

class FilmMapper @Inject constructor(
    private val getAverageFilmRatingsUseCase: GetAverageFilmRatingsUseCase
) {
    fun map(data: Film, userRating: Int?): FilmDto {
        return FilmDto(
            country = data.country,
            genres = data.genres,
            id = data.id,
            name = data.name,
            poster = data.poster,
            reviews = data.reviews,
            year = data.year,
            filmRating = getAverageFilmRatingsUseCase.execute(data.reviews, 1),
            userRating = userRating
        )
    }
}



