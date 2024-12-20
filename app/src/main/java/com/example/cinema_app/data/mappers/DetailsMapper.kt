package com.example.cinema_app.data.mappers

import com.example.cinema_app.data.movie.entities.MovieDetailsApi
import com.example.cinema_app.data.review.entities.ReviewApi
import com.example.cinema_app.data.review.mapper.toDomain
import com.example.cinema_app.domain.entities.Review
import com.example.cinema_app.presentation.entities.MovieDetailsUi
import javax.inject.Inject

class DetailsMapper @Inject constructor() {

    fun map(
        data: MovieDetailsApi,
        userReview: Review?,
        averageFilmRating: Double,
        filmInFavourite: Boolean
    ): MovieDetailsUi {
        return MovieDetailsUi(
            country = data.country,
            genreApis = data.genreApis.map { it.toDomain() },
            id = data.id,
            name = data.name,
            poster = data.poster,
            review = data.reviewApis.map { it.toDomain() },
            year = data.year,
            ageLimit = data.ageLimit,
            budget = data.budget,
            description = data.description,
            director = data.director,
            fees = data.fees,
            tagline = data.tagline,
            time = data.time,
            userReviewApiX = userReview,
            averageFilmRating = averageFilmRating,
            filmInFavourite = filmInFavourite
        )
    }

}