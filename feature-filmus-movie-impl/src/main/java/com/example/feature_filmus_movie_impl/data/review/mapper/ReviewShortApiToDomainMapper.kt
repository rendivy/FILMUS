package com.example.feature_filmus_movie_impl.data.review.mapper

import com.example.feature_filmus_movie_api.entities.ReviewShort
import com.example.feature_filmus_movie_impl.data.review.entities.ReviewShortApi

/**
 * @author y.yangel
 */
internal fun ReviewShortApi.toDomain(): ReviewShort {
    return ReviewShort(
        id = id,
        rating = rating,
    )
}