package com.example.cinema_app.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.domain.usecase.AddFavouriteMovieUseCase
import com.example.cinema_app.domain.usecase.AddUserReviewUseCase
import com.example.cinema_app.domain.usecase.DeleteFavouriteMovieUseCase
import com.example.cinema_app.domain.usecase.DeleteUserReviewUseCase
import com.example.cinema_app.domain.usecase.EditUserReviewUseCase
import com.example.cinema_app.domain.usecase.GetMovieDetailsFacade
import com.example.cinema_app.presentation.converter.DateConverter
import com.example.cinema_app.presentation.state.DetailsState
import com.example.cinema_app.ui.state.DetailsFavouriteContent
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
    private val movieDetailsUseCase: GetMovieDetailsFacade,
    private val dateConverter: DateConverter,
    private val deleteFavouriteMovieUseCase: DeleteFavouriteMovieUseCase,
    private val addUserReviewUseCase: AddUserReviewUseCase,
    private val deleteUserReviewUseCase: DeleteUserReviewUseCase,
    private val addUserFavouriteMovieUseCase: AddFavouriteMovieUseCase,
    private val editUserReviewUseCase: EditUserReviewUseCase,
) : ViewModel() {

    private val _detailsState = MutableStateFlow<DetailsState>(DetailsState.Initial)
    val detailsState: StateFlow<DetailsState> = _detailsState

    val reviewState: State<ReviewContent>
        get() = _reviewState
    private val _reviewState: MutableState<ReviewContent> = mutableStateOf(ReviewContent())

    val favouriteState: State<DetailsFavouriteContent>
        get() = _favouriteState

    private val _favouriteState: MutableState<DetailsFavouriteContent> = mutableStateOf(
        DetailsFavouriteContent(false)
    )


    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                401 -> {
                    _detailsState.value = DetailsState.Error(ErrorConstant.UNAUTHORIZED)
                }

                400 -> {
                    _reviewState.value = _reviewState.value.copy(
                        anonymousError = ErrorConstant.ANONYMOUS_ERROR,

                        )
                }
            }

            else -> {
                throw exception

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
        return dateConverter.convertDateToUi(date)
    }

    fun deleteUserReview(movieId: String, reviewId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUserReviewUseCase.execute(movieId, reviewId)
            _detailsState.value = DetailsState.Initial
            _reviewState.value = _reviewState.value.copy(
                anonymousError = null, isRatingChanged = true
            )
        }
    }

    private fun deleteFavouriteMovie(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavouriteMovieUseCase.execute(movieId = movieId)
        }
    }


    private fun addFavouriteMovie(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addUserFavouriteMovieUseCase.execute(movieId)
        }
    }


    fun addReview(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addUserReviewUseCase.execute(
                movieId,
                _reviewState.value.reviewText,
                _reviewState.value.rating.toInt(),
                _reviewState.value.isAnonymous
            )
            _detailsState.value = DetailsState.Initial
            _reviewState.value = _reviewState.value.copy(
                anonymousError = null,
                isRatingChanged = true
            )

        }

    }

    fun setFavouriteState(movieId: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            if (_favouriteState.value.filmInFavourite) {
                deleteFavouriteMovie(movieId = movieId)
                _favouriteState.value = _favouriteState.value.copy(
                    filmInFavourite = false
                )
            } else {
                addFavouriteMovie(movieId = movieId)
                _favouriteState.value = _favouriteState.value.copy(
                    filmInFavourite = true
                )
            }
        }

    }

    fun editReview(movieId: String, reviewId: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            editUserReviewUseCase.execute(
                movieId = movieId,
                reviewId = reviewId,
                reviewText = _reviewState.value.reviewText,
                rating = _reviewState.value.rating,
                isAnonymous = _reviewState.value.isAnonymous,
            )
            _detailsState.value = DetailsState.Initial
            _reviewState.value = _reviewState.value.copy(
                anonymousError = null,
                isRatingChanged = true
            )
        }
    }

    fun retry() {
        _detailsState.value = DetailsState.Initial
    }

    fun getMovieDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _detailsState.value = DetailsState.Loading
            val movieDetails = movieDetailsUseCase.execute(id, _reviewState)
            _detailsState.value = DetailsState.Content(movieDetails)
            _favouriteState.value = _favouriteState.value.copy(
                filmInFavourite = movieDetails.filmInFavourite
            )
        }
    }
}
