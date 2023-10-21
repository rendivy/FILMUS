package com.example.cinema_app.presentation

sealed class ValidationEvent{
    data object Success: ValidationEvent()
}
