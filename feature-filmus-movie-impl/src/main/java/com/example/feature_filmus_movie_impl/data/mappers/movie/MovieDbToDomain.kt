package com.example.feature_filmus_movie_impl.data.mappers.movie

import com.example.feature_filmus_movie_api.entities.Genre
import com.example.feature_filmus_movie_api.entities.movie.MovieWithRatings
import com.example.feature_filmus_movie_impl.database.entities.MovieDb

/**
 * @author y.yangel
 */
fun MovieDb.toDomain(): MovieWithRatings {
    return MovieWithRatings(
        id = id,
        country = country,
        name = name,
        poster = "",
        genres = genres.createGenreList(),
        reviewShortList = listOf(),
        year = year,
        userRating = userRating,
        filmRating = filmRating,
    )
}

private fun List<String>.createGenreList(): List<Genre> {
    return this.map {
        Genre(id = "", name = it)
    }
}