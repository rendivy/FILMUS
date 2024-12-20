package com.example.cinema_app.data.mappers

import com.example.cinema_app.data.api.review.AddReviewApi
import com.example.cinema_app.ui.state.ReviewContent

class ReviewMapper  {
    fun map(data: ReviewContent): AddReviewApi {
        return AddReviewApi(
            reviewText = data.reviewText,
            rating = data.rating.toInt(),
            isAnonymous = data.isAnonymous
        )
    }
}