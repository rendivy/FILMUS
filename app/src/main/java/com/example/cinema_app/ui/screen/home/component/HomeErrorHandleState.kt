package com.example.cinema_app.ui.screen.home.component

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import com.example.cinema_app.common.Constants
import com.example.cinema_app.presentation.HomeViewModel
import com.example.cinema_app.ui.navigation.NavigationRoutes
import com.example.cinema_app.ui.screen.errorUiScreen.ErrorUiScreen
import com.example.cinema_app.ui.screen.home.CachedFilmScreen
import retrofit2.HttpException

@Composable
fun HandleErrorState(
    navController: NavHostController,
    context: Context,
    homeViewModel: HomeViewModel,
    refreshState: LoadState,
    onClick: () -> Unit
) {
    val errorMessage = (refreshState as LoadState.Error).error
    if (errorMessage is HttpException) {
        when (errorMessage.code()) {
            401 -> {
                navController.popBackStack()
                navController.navigate(NavigationRoutes.Login.route)
                LaunchedEffect(Unit){
                    Toast.makeText(
                        context, Constants.UNAUTHORIZED,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            else -> {
                ErrorUiScreen(onClick)
            }
        }
    }
    else {
        AnimatedVisibility(true) {
            CachedFilmScreen(homeViewModel = homeViewModel, onClick = onClick)

        }
    }
}