package com.example.cinema_app.data.api.pagination

import kotlinx.serialization.Serializable


@Serializable
internal data class PaginationPageInfoApi(
    val currentPage: Int,
    val pageCount: Int,
    val pageSize: Int
)