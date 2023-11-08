package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.entity.Review

class GetAverageFilmRatingsUseCase {

    fun execute(data: List<Review>, precision: Int): Double {
        if (data.isEmpty()) {
            return 0.0
        }

        var rating = 0
        data.forEach {
            rating += it.rating
        }

        val averageRating = rating.toDouble() / data.size
        val format = String.format("%.${precision}f", averageRating)

        return format.toDouble()
    }
}