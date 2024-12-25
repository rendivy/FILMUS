package com.example.feature_filmus_movie_impl.data.entity

import com.example.feature_filmus_movie_impl.data.api.pagination.PaginationPageInfoApi
import com.example.feature_filmus_movie_impl.data.movie.entities.MovieShortApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PaginatorMovieList(
    @SerialName("pageInfo")
    val pageInfo: PaginationPageInfoApi,
    val movies: List<MovieShortApi>,
)

@Serializable
data class FavouriteMovieApi(
    val movies: List<MovieShortApi>,
)