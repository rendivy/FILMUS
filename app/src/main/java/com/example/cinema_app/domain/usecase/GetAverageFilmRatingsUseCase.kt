package com.example.cinema_app.domain.usecase

import com.example.cinema_app.data.entity.Film

class GetAverageFilmRatingsUseCase {

    fun execute(data: Film, precision: Int): Double {
        if (data.reviews.isEmpty()) {
            return 0.0
        }

        var rating = 0
        data.reviews.forEach {
            rating += it.rating
        }

        val averageRating = rating.toDouble() / data.reviews.size
        val format = String.format("%.${precision}f", averageRating)

        return format.toDouble()
    }
}