package com.example.cinema_app.ui.screen.home

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.cinema_app.presentation.HomeViewModel
import com.example.cinema_app.ui.screen.home.component.HandleErrorState
import com.example.cinema_app.ui.screen.home.component.HomeContentState
import com.example.cinema_app.ui.screen.home.component.HomeLoadingState

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    navHostController: NavHostController
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val context = LocalContext.current
    val moviesPaging = homeViewModel.moviePagingFlow.collectAsLazyPagingItems()
    val cardHeight = (screenHeight * 0.9f).coerceAtMost(497.dp)

    when (moviesPaging.loadState.refresh) {
        is LoadState.Error -> {
            HandleErrorState(
                navController = navHostController,
                context = context,
                refreshState = moviesPaging.loadState.refresh,
                onClick = { moviesPaging.retry() }
            )
        }

        is LoadState.Loading -> {
            HomeLoadingState(
                cardHeight = cardHeight
            )
        }

        is LoadState.NotLoading -> {
            HomeContentState(
                navController = navController,
                cardHeight = cardHeight,
                moviesPaging = moviesPaging
            )
        }

    }
}

