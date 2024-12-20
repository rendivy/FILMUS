package com.example.cinema_app.data.api.pagination

import kotlinx.serialization.Serializable


@Serializable
internal data class PageInfo(
    val pageInfo: PaginationPageInfoApi,
)