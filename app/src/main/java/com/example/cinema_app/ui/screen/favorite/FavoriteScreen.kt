package com.example.cinema_app.ui.screen.favorite

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.cinema_app.R
import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.common.NavigationConstant
import com.example.cinema_app.presentation.FavouritesMovieViewModel
import com.example.cinema_app.presentation.state.FavouriteState
import com.example.cinema_app.ui.screen.badRequestScreen.ErrorUiScreen
import com.example.cinema_app.ui.screen.favorite.section.MovieSection
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray400
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.InternBoldLarge
import com.example.cinema_app.ui.theme.TitleSmall
import com.example.cinema_app.ui.theme.padding100
import com.example.cinema_app.ui.theme.padding128
import com.example.cinema_app.ui.theme.padding16
import com.example.cinema_app.ui.theme.padding5

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    favouritesMovieViewModel: FavouritesMovieViewModel,
    navController: NavController,
    navHostController: NavHostController
) {
    val movieState by favouritesMovieViewModel.favouriteMovieState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        favouritesMovieViewModel.getFavouriteMovie()
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.favourite_main),
                    style = InternBoldLarge,
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Gray900,
                scrolledContainerColor = Gray900,
                navigationIconContentColor = Color.White,
                titleContentColor = Accent
            ),
        )
    }) {
        when (movieState) {


            is FavouriteState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AnimatedVisibility(visible = true) {
                        CircularProgressIndicator(
                            modifier = Modifier.width(padding128),
                            color = Accent,
                            trackColor = Gray400
                        )
                    }
                }

            }

            is FavouriteState.NoFavourite -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Gray900)
                        .padding(
                            top = padding100,
                            bottom = padding16,
                            start = padding16,
                            end = padding16
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = stringResource(id = R.string.no_favourite_label),
                        style = InternBoldLarge, color = Color.White, textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.padding(top = padding5),
                        text = stringResource(id = R.string.no_favourite_text),
                        style = TitleSmall, color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }

            is FavouriteState.Content -> {
                MovieSection(movieState = movieState, padding = it, navController = navController)
            }

            is FavouriteState.Error -> {
                when ((movieState as FavouriteState.Error).message) {
                    ErrorConstant.UNAUTHORIZED -> {
                        navHostController.popBackStack()
                        navHostController.navigate(NavigationConstant.LOGIN_ROUTE)
                        Toast.makeText(
                            navController.context,
                            "Ваша сессия закончилась, пожалуйста войдите снова!",
                            Toast.LENGTH_SHORT
                        ).show(
                        )
                    }

                    else -> {
                        ErrorUiScreen {
                            favouritesMovieViewModel.retry()
                        }
                    }
                }

            }

            else -> {}
        }


    }
}