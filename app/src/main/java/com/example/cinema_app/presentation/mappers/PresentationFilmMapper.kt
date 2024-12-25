package com.example.cinema_app.presentation.mappers

import com.example.cinema_app.presentation.entities.FavouriteMovieUi
import com.example.feature_filmus_movie_api.entities.movie.MovieShort

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