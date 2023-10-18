package com.example.cinema_app.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinema_app.presentation.UserAuthViewModel
import com.example.cinema_app.ui.screen.greetings.GreetingsScreen
import com.example.cinema_app.ui.screen.registration.RegistrationScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CinemaNavHost(userAuthViewModel: UserAuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Greetings") {
        composable("Greetings") {
            GreetingsScreen(navController = navController)
        }
        composable("Registration") {
            RegistrationScreen(userAuthViewModel = userAuthViewModel)
        }

    }
}