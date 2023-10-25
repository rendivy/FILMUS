package com.example.cinema_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.domain.usecase.GetFavouriteMovieUseCase
import com.example.cinema_app.presentation.state.FavouriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouritesMovieViewModel @Inject constructor(
    private val getFavouriteMovieUseCase: GetFavouriteMovieUseCase
) : ViewModel() {

    private val _favouriteMovieState = MutableStateFlow<FavouriteState>(FavouriteState.Initial)
    val favouriteMovieState: StateFlow<FavouriteState> = _favouriteMovieState


    fun getFavouriteMovie() {
        viewModelScope.launch {
            _favouriteMovieState.value = FavouriteState.Loading
            val favouriteMovie = getFavouriteMovieUseCase.execute()

            if (favouriteMovie.movies.isEmpty()) {
                _favouriteMovieState.value = FavouriteState.NoFavourite
            }
            else {
                _favouriteMovieState.value = FavouriteState.Content(favouriteMovie)
            }

        }

    }

}