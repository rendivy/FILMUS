package com.example.cinema_app.presentation.mappers

import com.example.cinema_app.ui.state.ReviewContent
import com.example.feature_filmus_movie_api.entities.Review

class UserReviewMapper {
    fun map(data: Review): ReviewContent {
        return ReviewContent(
            reviewText = data.reviewText,
            rating = data.rating.toFloat(),
            isAnonymous = data.isAnonymous
        )
    }
}