package com.example.cinema_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.domain.usecase.ConvertDateUseCase
import com.example.cinema_app.domain.usecase.GetMovieDetailsUseCase
import com.example.cinema_app.presentation.state.DetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    private val convertDateUseCase: ConvertDateUseCase
) : ViewModel() {
    private val _detailsState = MutableStateFlow<DetailsState>(DetailsState.Initial)
    val detailsState: StateFlow<DetailsState> = _detailsState


    fun convertDate(date: String): String {
        return convertDateUseCase.execute(date)
    }

    fun getMovieDetails(id: String) {
        viewModelScope.launch {
            _detailsState.value = DetailsState.Loading
            val movieDetails = movieDetailsUseCase.execute(id)
            _detailsState.value = DetailsState.Content(movieDetails)
        }
    }
}
