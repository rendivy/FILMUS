package com.example.cinema_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.cinema_app.data.mediator.MoviePagingSource
import com.example.cinema_app.data.repository.MoviesRepositoryImpl
import com.example.cinema_app.domain.entity.FilmDto
import com.example.cinema_app.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepositoryImpl,
    private val moviePagingSource: MoviePagingSource
) : ViewModel() {


    private val _movieState = MutableStateFlow<HomeState>(HomeState.Initial)
    val movieState: StateFlow<HomeState> = _movieState

    val moviePagingFlow: Flow<PagingData<FilmDto>> = Pager(PagingConfig(pageSize = 6)) {
        moviePagingSource
    }.flow.cachedIn(viewModelScope)


    fun getMovies() {
        viewModelScope.launch {
            _movieState.value = HomeState.Loading
            try {
                _movieState.value = HomeState.Content(moviesRepository.getMovies())
            } catch (e: Exception) {
                _movieState.value = HomeState.Error(e.message ?: "An unexpected error occurred")
            }

        }
    }
}
