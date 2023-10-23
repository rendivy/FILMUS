package com.example.cinema_app.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinema_app.presentation.LoginViewModel
import com.example.cinema_app.presentation.RegistrationViewModel
import com.example.cinema_app.ui.screen.greetings.GreetingsScreen
import com.example.cinema_app.ui.screen.login.LoginScreen
import com.example.cinema_app.ui.screen.registration.credentials.RegistrationScreen
import com.example.cinema_app.ui.screen.registration.password.RegistrationPasswordScreen
import com.example.cinema_app.ui.splash.LaunchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CinemaNavHost(
    userAuthViewModel: RegistrationViewModel,
    loginViewModel: LoginViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.LaunchScreen.route
    ) {
        composable(NavigationRoutes.Greetings.route) {
            GreetingsScreen(navController = navController)
        }
        composable(NavigationRoutes.LaunchScreen.route) {
            LaunchScreen(navController = navController)
        }
        composable(NavigationRoutes.RegistrationPasswordScreen.route) {
            RegistrationPasswordScreen(
                userAuthViewModel = userAuthViewModel,
                navController = navController
            )
        }
        composable(NavigationRoutes.Registration.route) {
            RegistrationScreen(
                userAuthViewModel = userAuthViewModel,
                navController = navController
            )
        }
        composable(NavigationRoutes.Login.route) {
            LoginScreen(
                loginViewModel = loginViewModel,
                navController = navController
            )
        }

    }
}



