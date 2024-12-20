package com.example.cinema_app.presentation.mappers

import com.example.cinema_app.domain.entities.movie.MovieShort
import com.example.cinema_app.presentation.entities.FavouriteMovieUi

class PresentationFilmMapper {

    fun map(movieShortData: MovieShort, userReview: Int?): FavouriteMovieUi {
        return FavouriteMovieUi(
            id = movieShortData.id,
            name = movieShortData.name,
            poster = movieShortData.poster,
            userReview = userReview
        )
    }
}