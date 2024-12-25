package com.example.cinema_app.ui.screen.favorite

import android.widget.Toast
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cinema_app.R
import com.example.cinema_app.presentation.FavouritesMovieViewModel
import com.example.cinema_app.presentation.common.ErrorConstant
import com.example.cinema_app.presentation.state.FavouriteState
import com.example.cinema_app.ui.screen.errorUiScreen.ErrorUiScreen
import com.example.cinema_app.ui.screen.favorite.section.FavouriteScreenSection
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.InternBoldLarge
import com.example.feature_filmus_movie_impl.data.Constants

@Composable
fun FavouriteScreen(
    favouritesMovieViewModel: FavouritesMovieViewModel,
    onCardClick: (String) -> Unit,
    onErrorButtonClick: () -> Unit,
) {
    val movieState by favouritesMovieViewModel.favouriteMovieState.collectAsStateWithLifecycle()

    FavouriteScreenInternal(
        state = movieState,
        onCardClick = onCardClick,
        onErrorButtonClick = onErrorButtonClick,
        onRetryClick = { favouritesMovieViewModel.retry() },
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FavouriteScreenInternal(
    state: FavouriteState,
    onRetryClick: () -> Unit,
    onCardClick: (String) -> Unit,
    onErrorButtonClick: () -> Unit,
) {
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
        when (state) {
            is FavouriteState.Loading -> FavouriteScreenSkeleton()
            is FavouriteState.NoFavourite -> NoFavouriteScreen()
            is FavouriteState.Content -> {
                FavouriteScreenSection(
                    movieState = state,
                    padding = it,
                    onCardClick = onCardClick
                )
            }

            is FavouriteState.Error -> FavouriteScreenError(
                errorMessage = state.message,
                onErrorButtonClick = onErrorButtonClick,
                onRetryClick = onRetryClick
            )

            is FavouriteState.Initial -> Unit
        }
    }
}

@Composable
private fun FavouriteScreenError(
    errorMessage: String,
    onErrorButtonClick: () -> Unit,
    onRetryClick: () -> Unit
) {
    val context = LocalContext.current
    when (errorMessage) {
        ErrorConstant.UNAUTHORIZED -> {
            onErrorButtonClick()
            Toast.makeText(context, Constants.UNAUTHORIZED, Toast.LENGTH_SHORT).show()
        }
        else -> ErrorUiScreen(onClick = onRetryClick)
    }
}
