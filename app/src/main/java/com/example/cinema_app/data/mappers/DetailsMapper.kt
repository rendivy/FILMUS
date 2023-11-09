package com.example.cinema_app.data.mappers

import com.example.cinema_app.data.entity.FilmDetails
import com.example.cinema_app.data.entity.ReviewX
import com.example.cinema_app.domain.entity.DetailsDTO
import javax.inject.Inject

class DetailsMapper @Inject constructor() {

    fun map(data: FilmDetails, userRating: ReviewX?, averageFilmRating: Double): DetailsDTO {
        return DetailsDTO(
            country = data.country,
            genres = data.genres,
            id = data.id,
            name = data.name,
            poster = data.poster,
            reviews = data.reviews,
            year = data.year,
            ageLimit = data.ageLimit,
            budget = data.budget,
            description = data.description,
            director = data.director,
            fees = data.fees,
            tagline = data.tagline,
            time = data.time,
            userReviewX = userRating,
            averageFilmRating = averageFilmRating
        )
    }

}