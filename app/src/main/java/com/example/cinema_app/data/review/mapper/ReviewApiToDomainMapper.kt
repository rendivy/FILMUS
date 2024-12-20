package com.example.cinema_app.data.review.mapper

import com.example.cinema_app.data.entity.AuthorApi
import com.example.cinema_app.data.review.entities.ReviewApi
import com.example.cinema_app.domain.entities.Author
import com.example.cinema_app.domain.entities.Review

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