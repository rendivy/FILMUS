package com.example.cinema_app.presentation.state

import com.example.cinema_app.presentation.entities.MovieDetailsUi

sealed interface DetailsState {
    data object Initial : DetailsState
    data class Content (val movie: MovieDetailsUi) : DetailsState
    data object Loading : DetailsState
    data class Error(val message: String) : DetailsState
}