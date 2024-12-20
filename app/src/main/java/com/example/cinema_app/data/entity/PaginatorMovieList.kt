package com.example.cinema_app.data.entity

import com.example.cinema_app.data.api.pagination.PaginationPageInfoApi
import com.example.cinema_app.data.movie.entities.MovieShortApi
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