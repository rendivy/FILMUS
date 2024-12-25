package com.example.feature_filmus_movie_impl.data.review.mapper

import com.example.feature_filmus_movie_api.entities.movie.FavouriteMovie
import com.example.feature_filmus_movie_impl.data.entity.FavouriteMovieApi
import com.example.feature_filmus_movie_impl.data.movie.mappers.toDomain

/**
 * @author y.yangel
 */
fun FavouriteMovieApi.toDomain(): FavouriteMovie {
    return FavouriteMovie(
        movies = movies.map { it.toDomain() }
    )
}