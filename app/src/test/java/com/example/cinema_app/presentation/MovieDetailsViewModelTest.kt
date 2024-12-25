package com.example.cinema_app.presentation

import app.cash.turbine.ReceiveTurbine
import app.cash.turbine.test
import com.example.cinema_app.presentation.common.ErrorConstant.BAD_REQUEST
import com.example.cinema_app.presentation.entities.MovieDetailsUi
import com.example.cinema_app.presentation.facade.GetMovieDetailsFacade
import com.example.cinema_app.presentation.state.DetailsState
import com.example.cinema_app.utils.CoroutineRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.exceptions.base.MockitoException
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

/**
 * @author y.yangel
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @Test
    fun `WHEN get movie details THEN movie details is loaded`() = runTest {
        with(createEnvironment()) {
            whenever(getMovieDetailsFacade.execute(any(), any())).thenReturn(stubMovieDetailsUi())
            viewModel.getMovieDetails(MOVIE_ID)

            viewModel.detailsState.test {
                checkInitialState()
                checkLoadingState()
                checkContentState(MOVIE_ID)
            }
        }
    }

    @Test
    fun `WHEN get movie details throw error THEN movie details state is error`() = runTest {
        with(createEnvironment()) {
            whenever(getMovieDetailsFacade.execute(any(), any())).thenThrow(MockitoException(""))
            viewModel.getMovieDetails(MOVIE_ID)

            viewModel.detailsState.test {
                checkInitialState()
                checkLoadingState()
                checkErrorState()
            }
        }
    }

    private suspend fun ReceiveTurbine<DetailsState>.checkInitialState() {
        val state = awaitItem()
        assertFalse(state is DetailsState.Loading)
        assertEquals(state, DetailsState.Initial)
    }

    private suspend fun ReceiveTurbine<DetailsState>.checkLoadingState() {
        val state = awaitItem()
        assertFalse(state is DetailsState.Content)
        assertEquals(state, DetailsState.Loading)
    }

    private suspend fun ReceiveTurbine<DetailsState>.checkContentState(id: String) {
        val state = awaitItem()
        assertFalse(state is DetailsState.Loading)
        assertEquals(state, DetailsState.Content(stubMovieDetailsUi()))
        val content = state as DetailsState.Content
        assertEquals(content.movie.id, id)
    }

    private suspend fun ReceiveTurbine<DetailsState>.checkErrorState() {
        val state = awaitItem()
        assertFalse(state is DetailsState.Loading)
        assertEquals(state, DetailsState.Error(BAD_REQUEST))
    }

    private fun stubMovieDetailsUi(): MovieDetailsUi {
        return MovieDetailsUi(
            id = MOVIE_ID,
            ageLimit = 0,
            budget = 100,
            country = "Russia",
            description = "description",
            director = "director",
            fees = 100,
            genreApis = emptyList(),
            name = "name",
            poster = "poster",
            review = emptyList(),
            tagline = "tagline",
            time = 1,
            year = 2021,
            filmInFavourite = false,
            averageFilmRating = 2.0,
            userReviewApiX = null,
        )
    }

    private fun createEnvironment(): Environment {
        val getMovieDetailsFacade = mock<GetMovieDetailsFacade>()
        val viewModel = MovieDetailsViewModel(
            getMovieDetailsFacade = getMovieDetailsFacade,
            dateConverter = mock(),
            deleteFavouriteMovieUseCase = mock(),
            addUserReviewUseCase = mock(),
            deleteUserReviewUseCase = mock(),
            addUserFavouriteMovieUseCase = mock(),
            editUserReviewUseCase = mock(),
        )
        return Environment(
            viewModel = viewModel,
            getMovieDetailsFacade = getMovieDetailsFacade,
        )
    }

    private class Environment(
        val viewModel: MovieDetailsViewModel,
        val getMovieDetailsFacade: GetMovieDetailsFacade,
    )


    private companion object {
        const val MOVIE_ID = "id"
    }
}