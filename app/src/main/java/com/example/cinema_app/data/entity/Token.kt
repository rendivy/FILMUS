package com.example.cinema_app.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Token(@SerialName("token") val token: String)
