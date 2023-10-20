package com.example.cinema_app.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinema_app.presentation.UserAuthViewModel
import com.example.cinema_app.ui.screen.greetings.GreetingsScreen
import com.example.cinema_app.ui.screen.login.LoginScreen
import com.example.cinema_app.ui.screen.registration.RegistrationScreen
import com.example.cinema_app.ui.screen.splash.LaunchScreen
import com.example.cinema_app.ui.theme.CinemaappTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CinemaNavHost(
    userAuthViewModel: UserAuthViewModel,
) {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val isStatusBarEnabled = currentRoute != NavigationRoutes.LaunchScreen.route
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.LaunchScreen.route
    ) {
        composable(NavigationRoutes.Greetings.route) {
            CinemaappTheme(isStatusBarEnabled) {
                GreetingsScreen(navController = navController)
            }
        }
        composable(NavigationRoutes.LaunchScreen.route) {
            LaunchScreen(navController = navController)
        }
        composable(NavigationRoutes.Registration.route) {
            RegistrationScreen(
                userAuthViewModel = userAuthViewModel,
                navController = navController
            )
        }
        composable(NavigationRoutes.Login.route) {
            LoginScreen(
                userAuthViewModel = userAuthViewModel,
                navController = navController
            )
        }

    }
}



