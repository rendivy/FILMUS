package com.example.cinema_app.presentation.state

sealed interface RegistrationState {
    data object Initial : RegistrationState
    data object Loading : RegistrationState
    data object Success : RegistrationState
    data class Error(val message: String) : RegistrationState
}


