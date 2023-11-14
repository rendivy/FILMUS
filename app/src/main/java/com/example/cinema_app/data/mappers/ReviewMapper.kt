package com.example.cinema_app.data.mappers

import com.example.cinema_app.data.entity.AddReviewBody
import com.example.cinema_app.ui.state.ReviewContent

class ReviewMapper  {
    fun map(data: ReviewContent): AddReviewBody {
        return AddReviewBody(
            reviewText = data.reviewText,
            rating = data.rating.toInt(),
            isAnonymous = data.isAnonymous
        )
    }
}