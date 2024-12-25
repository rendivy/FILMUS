package com.example.feature_filmus_movie_impl.data.mappers

import com.example.feature_filmus_movie_api.entities.ReviewShort
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

        return if (averageRating >= 0) {
            BigDecimal(averageRating).setScale(
                precision,
                RoundingMode.HALF_UP
            ).toDouble()
        } else {
            0.0
        }
    }
}