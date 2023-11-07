package com.example.cinema_app.presentation.state

import com.example.cinema_app.ui.state.ProfileContent

sealed interface ProfileState {
    data object Initial : ProfileState
    data class Content(val profileCredentials: ProfileContent) : ProfileState
    data object Loading : ProfileState
    data object Successful : ProfileState
    data class Error(val errorMessage: String) : ProfileState
}