package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.entity.AddReviewBody
import com.example.cinema_app.data.mappers.ReviewMapper
import com.example.cinema_app.ui.state.ReviewContent
import javax.inject.Inject

class ReviewMapperUseCase @Inject constructor(private val reviewMapper: ReviewMapper)  {

    fun execute(data: ReviewContent): AddReviewBody {
        return reviewMapper.map(data)
    }

}