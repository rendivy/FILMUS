package com.example.cinema_app.data.movie.mappers

import com.example.cinema_app.database.entities.MovieDb
import com.example.cinema_app.domain.entities.movie.MovieShort

/**
 * @author y.yangel
 */
class MovieShortToDbMapper : (MovieShort, Int?, Double?) -> MovieDb {

    override fun invoke(from: MovieShort, userRating: Int?, filmRating: Double?): MovieDb {
        return MovieDb(
            id = from.id,
            country = from.country,
            name = from.name,
            year = from.year,
            genres = from.extractGenresFromFilm(),
            userRating = userRating,
            filmRating = filmRating,
        )
    }

    private fun MovieShort.extractGenresFromFilm(): List<String> {
        return genres.map { it.name }
    }
}


