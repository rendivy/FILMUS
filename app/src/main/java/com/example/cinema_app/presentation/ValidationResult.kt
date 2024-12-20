package com.example.cinema_app.presentation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)
