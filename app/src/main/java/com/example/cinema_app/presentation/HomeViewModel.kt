package com.example.cinema_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.cinema_app.domain.entity.FilmDTO
import com.example.cinema_app.pagination.MoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val moviePagingSource: MoviePagingSource) :
    ViewModel() {
    val moviePagingFlow: Flow<PagingData<FilmDTO>> = Pager(PagingConfig(pageSize = 6)) {
        moviePagingSource }
        .flow.cachedIn(viewModelScope)

}