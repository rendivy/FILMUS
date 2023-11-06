package com.example.cinema_app.presentation.state

import com.example.cinema_app.data.entity.FilmDetails

sealed interface DetailsState {
    data object Initial : DetailsState
    data class Content (val movie: FilmDetails) : DetailsState
    data object Loading : DetailsState
    data class Error(val message: String) : DetailsState
}