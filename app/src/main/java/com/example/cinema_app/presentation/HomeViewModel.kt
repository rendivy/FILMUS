package com.example.cinema_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.data.repository.MoviesRepositoryImpl
import com.example.cinema_app.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val moviesRepository: MoviesRepositoryImpl) :
    ViewModel() {

    private val _movieState = MutableStateFlow<HomeState>(HomeState.Initial)
    val movieState: StateFlow<HomeState> = _movieState



    fun getMovies() {
        viewModelScope.launch() {
            _movieState.value = HomeState.Loading
            try {
                _movieState.value = HomeState.Content(moviesRepository.getMovies())
            } catch (e: Exception) {
                _movieState.value = HomeState.Error(e.message ?: "An unexpected error occured")
            }

        }
    }
}