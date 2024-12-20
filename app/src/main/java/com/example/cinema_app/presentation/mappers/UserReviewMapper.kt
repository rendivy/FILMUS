package com.example.cinema_app.presentation.mappers

import com.example.cinema_app.data.review.entities.ReviewApi
import com.example.cinema_app.domain.entities.Review
import com.example.cinema_app.ui.state.ReviewContent

class UserReviewMapper {
    fun map(data: Review): ReviewContent {
        return ReviewContent(
            reviewText = data.reviewText,
            rating = data.rating.toFloat(),
            isAnonymous = data.isAnonymous
        )
    }
}