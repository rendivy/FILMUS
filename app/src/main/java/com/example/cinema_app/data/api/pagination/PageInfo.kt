package com.example.cinema_app.data.api.pagination

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageInfo(
    @SerialName("pageInfo")
    val pageInfo: PaginationPageInfoApi,
)