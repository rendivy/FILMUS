package com.example.feature_filmus_movie_impl.data.api.pagination

import kotlinx.serialization.Serializable


@Serializable
data class PaginationPageInfoApi(
    val currentPage: Int,
    val pageCount: Int,
    val pageSize: Int
)