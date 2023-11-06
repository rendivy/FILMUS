package com.example.cinema_app.presentation.state

import com.example.cinema_app.data.entity.Movie

sealed interface FavouriteState {
    data object Initial : FavouriteState
    data class Content (val movie: Movie) : FavouriteState
    data object Loading : FavouriteState
    data class Error(val message: String) : FavouriteState
    data object NoFavourite : FavouriteState
}