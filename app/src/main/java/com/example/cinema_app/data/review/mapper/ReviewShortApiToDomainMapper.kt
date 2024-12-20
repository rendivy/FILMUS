package com.example.cinema_app.data.review.mapper

import com.example.cinema_app.data.review.entities.ReviewShortApi
import com.example.cinema_app.domain.entities.ReviewShort

/**
 * @author y.yangel
 */
internal fun ReviewShortApi.toDomain(): ReviewShort {
    return ReviewShort(
        id = id,
        rating = rating,
    )
}