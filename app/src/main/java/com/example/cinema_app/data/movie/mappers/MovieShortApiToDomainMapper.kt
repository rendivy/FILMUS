package com.example.cinema_app.data.movie.mappers

import com.example.cinema_app.data.movie.entities.MovieShortApi
import com.example.cinema_app.data.review.mapper.toDomain
import com.example.cinema_app.domain.entities.movie.MovieShort

/**
 * @author y.yangel
 */
fun MovieShortApi.toDomain(): MovieShort {
    return MovieShort(
        id = id,
        country = country,
        name = name,
        poster = poster,
        year = year,
        genres = genreApis.map { it.toDomain() },
        reviewShortList = reviewShortList.map { it.toDomain() },
    )
}


