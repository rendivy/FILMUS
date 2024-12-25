package com.example.feature_filmus_movie_impl.data.review.mapper

import com.example.feature_filmus_movie_api.entities.Genre
import com.example.feature_filmus_movie_impl.data.movie.entities.GenreApi

/**
 * @author y.yangel
 */
fun GenreApi.toDomain(): Genre {
    return Genre(
        id = id,
        name = name,
    )
}