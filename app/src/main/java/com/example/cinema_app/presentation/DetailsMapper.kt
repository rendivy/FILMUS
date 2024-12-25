package com.example.cinema_app.presentation

import com.example.cinema_app.presentation.entities.MovieDetailsUi
import com.example.feature_filmus_movie_api.entities.Review
import com.example.feature_filmus_movie_api.entities.movie.MovieDetails
import javax.inject.Inject

class DetailsMapper @Inject constructor() {

    fun map(
        data: MovieDetails,
        userReview: Review?,
        averageFilmRating: Double,
        filmInFavourite: Boolean
    ): MovieDetailsUi {
        return MovieDetailsUi(
            country = data.country,
            genreApis = data.genres,
            id = data.id,
            name = data.name,
            poster = data.poster,
            review = data.reviews,
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