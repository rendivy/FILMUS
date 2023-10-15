package com.example.cinema_app.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TokenBody(
    @SerialName("token") val authToken: String
)
