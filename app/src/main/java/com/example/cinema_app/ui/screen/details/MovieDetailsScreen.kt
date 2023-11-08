@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cinema_app.ui.screen.details

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cinema_app.presentation.MovieDetailsViewModel
import com.example.cinema_app.presentation.state.DetailsState
import com.example.cinema_app.ui.screen.details.component.DetailsLoadingScreen
import com.example.cinema_app.ui.screen.details.component.MovieDetailsContent


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MovieDetailsScreen(
    movieId: String,
    movieRating: String,
    movieDetailsViewModel: MovieDetailsViewModel
) {
    val detailsState by movieDetailsViewModel.detailsState.collectAsStateWithLifecycle()

    when (detailsState) {
        is DetailsState.Initial -> {
            movieDetailsViewModel.getMovieDetails(movieId)
        }

        is DetailsState.Loading -> {
            DetailsLoadingScreen()
        }

        is DetailsState.Content -> {
            val content = (detailsState as DetailsState.Content).movie
            MovieDetailsContent(content, movieRating, movieDetailsViewModel)
        }

        else -> {}
    }

}











