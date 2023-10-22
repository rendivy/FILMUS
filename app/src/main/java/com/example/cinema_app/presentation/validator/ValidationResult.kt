package com.example.cinema_app.presentation.validator

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)
