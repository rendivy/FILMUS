package com.example.cinema_app.data.mappers

import com.example.cinema_app.data.entity.FilmDetailsApi
import com.example.cinema_app.data.entity.ReviewX
import javax.inject.Inject

class DetailsMapper @Inject constructor() {

    fun map(
        data: FilmDetailsApi,
        userRating: ReviewX?,
        averageFilmRating: Double,
        filmInFavourite: Boolean
    ): com.example.cinema_app.domain.entity.FilmDetails {
        return com.example.cinema_app.domain.entity.FilmDetails(
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
            averageFilmRating = averageFilmRating,
            filmInFavourite = filmInFavourite
        )
    }

}