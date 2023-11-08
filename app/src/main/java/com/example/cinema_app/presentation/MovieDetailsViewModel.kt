package com.example.cinema_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.domain.usecase.AddUserReviewUseCase
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
    private val convertDateUseCase: ConvertDateUseCase,
    private val addUserReviewUseCase: AddUserReviewUseCase
) : ViewModel() {
    private val _detailsState = MutableStateFlow<DetailsState>(DetailsState.Initial)
    val detailsState: StateFlow<DetailsState> = _detailsState


    fun convertDate(date: String): String {
        return convertDateUseCase.execute(date)
    }

    fun addReview(movieId: String, reviewText: String, rating: Int, isAnonymous: Boolean) {
        viewModelScope.launch {
            addUserReviewUseCase.execute(movieId, reviewText, rating, isAnonymous)
            _detailsState.value = DetailsState.Initial
        }

    }

    fun getMovieDetails(id: String) {
        viewModelScope.launch {
            _detailsState.value = DetailsState.Loading
            val movieDetails = movieDetailsUseCase.execute(id)
            _detailsState.value = DetailsState.Content(movieDetails)
        }
    }
}
