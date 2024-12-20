package com.example.cinema_app.data.mappers.movie

import com.example.cinema_app.database.entities.MovieDb
import com.example.cinema_app.domain.entities.Genre
import com.example.cinema_app.domain.entities.movie.MovieWithRatings

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