package com.example.cinema_app.presentation.state

sealed interface LoginState {
    data object Initial: LoginState
    data object Success: LoginState
    data object Loading: LoginState
    data class Error(val message: String): LoginState
}