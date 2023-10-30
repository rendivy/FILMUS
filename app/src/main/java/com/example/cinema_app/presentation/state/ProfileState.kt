package com.example.cinema_app.presentation.state

import com.example.cinema_app.data.entity.ProfileCredentials

sealed interface ProfileState {
    data object Initial : ProfileState
    data class Content(val profileCredentials: ProfileCredentials) : ProfileState
    data object Loading : ProfileState
    data object Successful : ProfileState
    data object Error : ProfileState
}