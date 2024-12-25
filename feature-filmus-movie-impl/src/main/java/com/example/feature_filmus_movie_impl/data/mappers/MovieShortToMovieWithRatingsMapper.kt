package com.example.feature_filmus_movie_impl.data.mappers

import com.example.feature_filmus_movie_api.entities.movie.MovieShort
import com.example.feature_filmus_movie_api.entities.movie.MovieWithRatings

class MovieShortToMovieWithRatingsMapper : (MovieShort, Int?, Double) -> MovieWithRatings {

    override fun invoke(data: MovieShort, userRating: Int?, filmRating: Double): MovieWithRatings {
        return MovieWithRatings(
            country = data.country,
            genres = data.genres,
            id = data.id,
            name = data.name,
            poster = data.poster,
            reviewShortList = data.reviewShortList,
            year = data.year,
            filmRating = filmRating,
            userRating = userRating,
        )
    }
}



