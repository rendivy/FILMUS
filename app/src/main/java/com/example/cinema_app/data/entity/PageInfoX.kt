package com.example.cinema_app.data.entity

import kotlinx.serialization.Serializable


@Serializable
data class PageInfoX(
    val currentPage: Int,
    val pageCount: Int,
    val pageSize: Int
)