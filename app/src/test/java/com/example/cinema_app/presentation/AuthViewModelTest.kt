package com.example.cinema_app.presentation

import app.cash.turbine.test
import com.example.cinema_app.utils.CoroutineRule
import com.example.feature_filmus_movie_api.usecase.auth.IsUserLoggedUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author y.yangel
 */
@OptIn(ExperimentalCoroutinesApi::class)
internal class AuthViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @Test
    fun `WHEN is user logged THEN token state is true`() = runTest {
        with(createEnvironment()) {
            whenever(isUserLoggedUseCase()).thenReturn(true)
            val viewModel = createViewModel(isUserLoggedUseCase)
            advanceUntilIdle()

            viewModel.tokenState.test {
                val state = awaitItem()
                assertEquals(expected = true, state)
            }
        }
    }

    @Test
    fun `WHEN is user logged THEN token state is false`() = runTest {
        with(createEnvironment()) {
            whenever(isUserLoggedUseCase()).thenReturn(false)
            val viewModel = createViewModel(isUserLoggedUseCase)
            advanceUntilIdle()

            viewModel.tokenState.test {
                val state = awaitItem()
                assertEquals(expected = false, state)
            }
        }
    }

    private fun createViewModel(isUserLoggedUseCase: IsUserLoggedUseCase): AuthViewModel {
        return AuthViewModel(
            isUserLoggedUseCase = isUserLoggedUseCase,
        )
    }

    private fun createEnvironment(): Environment {
        val isUserLoggedUseCase = mock<IsUserLoggedUseCase>()
        return Environment(
            isUserLoggedUseCase = isUserLoggedUseCase,
        )
    }

    private class Environment(val isUserLoggedUseCase: IsUserLoggedUseCase)
}