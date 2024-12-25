package com.example.feature_filmus_movie_impl.data.review.mapper

import com.example.feature_filmus_movie_api.entities.movie.MovieDetails
import com.example.feature_filmus_movie_impl.data.movie.entities.MovieDetailsApi

/**
 * @author y.yangel
 */
internal fun MovieDetailsApi.toDomain(): MovieDetails {
    return MovieDetails(
        ageLimit = this.ageLimit,
        budget = this.budget,
        country = this.country,
        description = this.description,
        director = this.director,
        fees = this.fees,
        genres = this.genreApis.map { it.toDomain() },
        id = this.id,
        name = this.name,
        poster = this.poster,
        tagline = this.tagline,
        time = this.time,
        year = this.year,
        reviews = this.reviewApis.map { it.toDomain() },
    )
}