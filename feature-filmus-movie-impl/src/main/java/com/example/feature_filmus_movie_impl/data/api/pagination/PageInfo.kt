package com.example.feature_filmus_movie_impl.data.api.pagination

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageInfo(
    @SerialName("pageInfo")
    val pageInfo: PaginationPageInfoApi,
)