package com.example.feature_filmus_movie_impl.data.movie.mappers

import com.example.feature_filmus_movie_api.entities.movie.MovieShort
import com.example.feature_filmus_movie_impl.database.entities.MovieDb

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


