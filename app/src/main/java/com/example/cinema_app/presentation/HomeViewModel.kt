package com.example.cinema_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.cinema_app.data.dbentity.MovieDb
import com.example.cinema_app.data.pagination.MoviePagingSource
import com.example.cinema_app.domain.entity.FilmItemUi
import com.example.cinema_app.domain.usecase.GetAllCachedFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviePagingSource: MoviePagingSource,
    private val getAllCachedFilmsUseCase: GetAllCachedFilmsUseCase) : ViewModel() {

    private val _movieState = MutableStateFlow<List<MovieDb>>(emptyList())
    val movieState: StateFlow<List<MovieDb>> = _movieState

    val moviePagingFlow: Flow<PagingData<FilmItemUi>> = Pager(PagingConfig(pageSize = 6)) {
        moviePagingSource
    }.flow.cachedIn(viewModelScope)

    fun getAllCachedFilms() {
        viewModelScope.launch(Dispatchers.IO)
        {
            _movieState.value = getAllCachedFilmsUseCase.execute()
        }
    }

}