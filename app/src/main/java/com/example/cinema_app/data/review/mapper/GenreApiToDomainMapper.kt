package com.example.cinema_app.data.review.mapper

import com.example.cinema_app.data.movie.entities.GenreApi
import com.example.cinema_app.domain.entities.Genre

/**
 * @author y.yangel
 */
fun GenreApi.toDomain(): Genre {
    return Genre(
        id = id,
        name = name,
    )
}