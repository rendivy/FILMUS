package com.example.cinema_app.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.common.Constants
import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.domain.usecase.AddFavouriteMovieUseCase
import com.example.cinema_app.domain.usecase.AddUserReviewUseCase
import com.example.cinema_app.domain.usecase.ConvertDateUseCase
import com.example.cinema_app.domain.usecase.DeleteUserReviewUseCase
import com.example.cinema_app.domain.usecase.EditUserReviewUseCase
import com.example.cinema_app.domain.usecase.GetMovieDetailsUseCase
import com.example.cinema_app.presentation.state.DetailsState
import com.example.cinema_app.ui.state.ReviewContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    private val convertDateUseCase: ConvertDateUseCase,
    private val addUserReviewUseCase: AddUserReviewUseCase,
    private val deleteUserReviewUseCase: DeleteUserReviewUseCase,
    private val addUserFavouriteMovieUseCase: AddFavouriteMovieUseCase,
    private val editUserReviewUseCase: EditUserReviewUseCase
) : ViewModel() {


    private val _detailsState = MutableStateFlow<DetailsState>(DetailsState.Initial)
    val detailsState: StateFlow<DetailsState> = _detailsState

    val reviewState: State<ReviewContent>
        get() = _reviewState
    private val _reviewState: MutableState<ReviewContent> = mutableStateOf(
        ReviewContent(
            reviewText = Constants.EMPTY_STRING,
            rating = 0f
        )
    )

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                401 -> {
                    _detailsState.value = DetailsState.Error(ErrorConstant.UNAUTHORIZED)
                }

                400 -> {
                    _reviewState.value = _reviewState.value.copy(
                        anonymousError = ErrorConstant.ANONYMOUS_ERROR
                    )
                }
            }

            else -> {
                _detailsState.value = DetailsState.Error(ErrorConstant.BAD_REQUEST)
            }
        }
    }

    fun setReviewText(text: String) {
        _reviewState.value = _reviewState.value.copy(
            reviewText = text
        )
    }

    fun setAnonymous(isAnonymous: Boolean) {
        _reviewState.value = _reviewState.value.copy(
            isAnonymous = isAnonymous
        )
    }

    fun setRating(rating: Float) {
        _reviewState.value = _reviewState.value.copy(
            rating = rating
        )
    }

    fun convertDate(date: String): String {
        return convertDateUseCase.execute(date)
    }

    fun deleteUserReview(movieId: String, reviewId: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            deleteUserReviewUseCase.execute(movieId, reviewId)
            _detailsState.value = DetailsState.Initial
            _reviewState.value = _reviewState.value.copy(
                anonymousError = null
            )
        }

    }


    fun addFavouriteMovie(movieId: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            addUserFavouriteMovieUseCase.execute(movieId)
        }
    }


    fun addReview(movieId: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            addUserReviewUseCase.execute(
                movieId,
                _reviewState.value.reviewText,
                _reviewState.value.rating.toInt(),
                _reviewState.value.isAnonymous
            )
            _detailsState.value = DetailsState.Initial
            _reviewState.value = _reviewState.value.copy(
                anonymousError = null
            )

        }

    }


    fun editReview(movieId: String, reviewId: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            editUserReviewUseCase.execute(
                _reviewState.value,
                movieId,
                reviewId
            )
            _detailsState.value = DetailsState.Initial
            _reviewState.value = _reviewState.value.copy(
                anonymousError = null
            )
        }
    }

    fun retry() {
        _detailsState.value = DetailsState.Initial
    }

    fun getMovieDetails(id: String) {
        viewModelScope.launch(exceptionHandler) {
            _detailsState.value = DetailsState.Loading
            val movieDetails = movieDetailsUseCase.execute(id, _reviewState)
            _detailsState.value = DetailsState.Content(movieDetails)
        }
    }
}
