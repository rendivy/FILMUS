package com.example.feature_filmus_movie_impl.data.movie.mappers

import com.example.feature_filmus_movie_api.entities.movie.MovieShort
import com.example.feature_filmus_movie_impl.data.movie.entities.MovieShortApi
import com.example.feature_filmus_movie_impl.data.review.mapper.toDomain

/**
 * @author y.yangel
 */
internal fun MovieShortApi.toDomain(): MovieShort {
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


