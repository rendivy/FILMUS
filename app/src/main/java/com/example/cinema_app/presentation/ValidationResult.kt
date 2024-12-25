package com.example.cinema_app.presentation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)

enum class ErrorType {
    EMAIL,
    PASSWORD,
    CONFIRM_PASSWORD,
    EMPTY,
    LOGIN,
    NAME,
    DATE,
}