package com.example.cinema_app.presentation.mappers

import com.example.cinema_app.data.entity.ReviewX
import com.example.cinema_app.ui.state.ReviewContent

class UserReviewMapper {
    fun map(data: ReviewX): ReviewContent {
        return ReviewContent(
            reviewText = data.reviewText,
            rating = data.rating.toFloat(),
            isAnonymous = data.isAnonymous
        )
    }
}