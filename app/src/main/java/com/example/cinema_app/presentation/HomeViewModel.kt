package com.example.cinema_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.feature_filmus_movie_api.entities.movie.MovieWithRatings
import com.example.feature_filmus_movie_api.usecase.GetAllCachedFilmsUseCase
import com.example.feature_filmus_movie_impl.data.pagination.MoviePagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviePagingRepository: MoviePagingRepository,
    private val getAllCachedFilmsUseCase: GetAllCachedFilmsUseCase
) : ViewModel() {

    private val _movieState = MutableStateFlow<List<MovieWithRatings>>(emptyList())
    val movieState: StateFlow<List<MovieWithRatings>> = _movieState

    val moviePagingFlow: Flow<PagingData<MovieWithRatings>> = Pager(PagingConfig(pageSize = 6)) {
        moviePagingRepository
    }.flow.cachedIn(viewModelScope)

    fun getAllCachedFilms() {
        viewModelScope.launch()
        {
            _movieState.value = getAllCachedFilmsUseCase()
        }
    }

}