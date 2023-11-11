package com.example.cinema_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.domain.usecase.GetFavouriteMovieWithRatingUseCase
import com.example.cinema_app.presentation.state.FavouriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class FavouritesMovieViewModel @Inject constructor(
    private val getFavouriteMovieWithRatingUseCase: GetFavouriteMovieWithRatingUseCase,
) : ViewModel() {

    private val _favouriteMovieState = MutableStateFlow<FavouriteState>(FavouriteState.Initial)
    val favouriteMovieState: StateFlow<FavouriteState> = _favouriteMovieState

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                401 -> {
                    _favouriteMovieState.value = FavouriteState.Error(ErrorConstant.UNAUTHORIZED)
                }
            }

            else -> {
                _favouriteMovieState.value = FavouriteState.Error(ErrorConstant.BAD_REQUEST)
            }
        }
    }

    fun retry() {
        getFavouriteMovie()
    }


    fun getFavouriteMovie() {
        viewModelScope.launch(errorHandler) {
            _favouriteMovieState.value = FavouriteState.Loading
            val favouriteMovie = getFavouriteMovieWithRatingUseCase.execute()
            if (favouriteMovie.isEmpty()) {
                _favouriteMovieState.value = FavouriteState.NoFavourite
            } else {
                _favouriteMovieState.value = FavouriteState.Content(favouriteMovie)
            }
        }
    }

}