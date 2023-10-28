package com.example.cinema_app.presentation.state

import com.example.cinema_app.data.entity.Movie

sealed interface HomeState {
    data object Loading : HomeState
    data object Initial : HomeState
    data class Content(val movie: Movie) : HomeState
    data class Error(val exception: String) : HomeState
}