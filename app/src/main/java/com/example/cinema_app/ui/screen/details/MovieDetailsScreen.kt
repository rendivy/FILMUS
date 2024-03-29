@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cinema_app.ui.screen.details

import android.widget.Toast
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cinema_app.R
import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.presentation.MovieDetailsViewModel
import com.example.cinema_app.presentation.state.DetailsState
import com.example.cinema_app.ui.navigation.NavigationRoutes
import com.example.cinema_app.ui.screen.errorUiScreen.ErrorUiScreen
import com.example.cinema_app.ui.screen.details.component.DetailsLoadingScreen
import com.example.cinema_app.ui.screen.details.component.MovieDetailsContent


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MovieDetailsScreen(
    movieId: String,
    movieRating: String,
    movieDetailsViewModel: MovieDetailsViewModel,
    navHostController: NavController,
    navController: NavController
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
            MovieDetailsContent(
                content,
                movieDetailsViewModel,
                navController = navController
            )
        }

        else -> {
            val errorMessage = (detailsState as DetailsState.Error).message
            if (errorMessage == ErrorConstant.UNAUTHORIZED) {
                navHostController.popBackStack()
                navHostController.navigate(NavigationRoutes.Login.route)
                Toast.makeText(
                    navHostController.context,
                    stringResource(id = R.string.unauthorized),
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                ErrorUiScreen {
                    movieDetailsViewModel.retry()
                }
            }
        }
    }

}











