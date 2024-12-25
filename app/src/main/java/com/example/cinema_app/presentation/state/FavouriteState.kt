package com.example.cinema_app.presentation.state

import androidx.compose.runtime.Immutable
import com.example.cinema_app.presentation.entities.FavouriteMovieUi

@Immutable
sealed interface FavouriteState {
    data object Initial : FavouriteState
    data class Content (val movie: List<FavouriteMovieUi>) : FavouriteState
    data object Loading : FavouriteState
    data class Error(val message: String) : FavouriteState
    data object NoFavourite : FavouriteState
}

