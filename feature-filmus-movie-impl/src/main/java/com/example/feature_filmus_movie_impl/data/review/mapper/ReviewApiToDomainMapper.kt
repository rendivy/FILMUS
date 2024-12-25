package com.example.feature_filmus_movie_impl.data.review.mapper

import com.example.feature_filmus_movie_api.entities.Author
import com.example.feature_filmus_movie_api.entities.Review
import com.example.feature_filmus_movie_impl.data.entity.AuthorApi
import com.example.feature_filmus_movie_impl.data.review.entities.ReviewApi

/**
 * @author y.yangel
 */
fun ReviewApi.toDomain(): Review {
    return Review(
        author = author?.toDomain(),
        id = id,
        createDateTime = createDateTime,
        rating = rating,
        isAnonymous = isAnonymous,
        reviewText = reviewText
    )
}

private fun AuthorApi.toDomain(): Author {
    return Author(
        avatar = avatar,
        nickName = nickName,
        userId = userId
    )
}