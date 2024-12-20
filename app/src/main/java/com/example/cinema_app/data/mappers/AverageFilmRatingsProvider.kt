package com.example.cinema_app.data.mappers

import com.example.cinema_app.domain.entities.ReviewShort
import java.math.BigDecimal
import java.math.RoundingMode

class AverageFilmRatingsProvider {

    operator fun invoke(data: List<ReviewShort>, precision: Int = 1): Double {
        if (data.isEmpty()) return 0.0
        var rating = 0
        data.forEach {
            rating += it.rating
        }
        val averageRating = rating.toDouble() / data.size

        return BigDecimal(averageRating).setScale(precision, RoundingMode.HALF_UP).toDouble()
    }
}