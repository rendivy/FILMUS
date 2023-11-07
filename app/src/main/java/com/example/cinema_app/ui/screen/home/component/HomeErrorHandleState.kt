package com.example.cinema_app.ui.screen.home.component

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import com.example.cinema_app.ui.navigation.NavigationRoutes
import com.example.cinema_app.ui.screen.badRequestScreen.ErrorUiScreen
import retrofit2.HttpException

@Composable
fun HandleErrorState(
    navController: NavHostController,
    context: Context,
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
                        context, "Ваша сессия закончилась, пожалуйста войдите снова",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            else -> {
                ErrorUiScreen(onClick)
            }
        }
    } else {
        ErrorUiScreen(onClick)
    }
}