package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.entity.AddReviewBody
import com.example.cinema_app.data.mappers.ReviewMapper
import com.example.cinema_app.ui.state.ReviewContent
import javax.inject.Inject

class ReviewMapperUseCase @Inject constructor(reviewMapper: ReviewMapper) {

    fun map(data: ReviewContent): AddReviewBody {
        return AddReviewBody(
            reviewText = data.reviewText,
            rating = data.rating.toInt(),
            isAnonymous = data.isAnonymous
        )
    }

}