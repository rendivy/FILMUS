package com.example.cinema_app.ui.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinema_app.presentation.RegistrationViewModel
import com.example.cinema_app.ui.screen.MainScreen
import com.example.cinema_app.ui.screen.greetings.GreetingsScreen
import com.example.cinema_app.ui.screen.login.LoginScreen
import com.example.cinema_app.ui.screen.registration.credentials.RegistrationScreen
import com.example.cinema_app.ui.screen.registration.password.RegistrationPasswordScreen
import com.example.cinema_app.ui.splash.LaunchScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CinemaNavHost(registrationViewModel: RegistrationViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.LaunchScreen.route
    ) {
        composable(NavigationRoutes.Main.route) {
            MainScreen(navHostController = navController)
        }
        composable(NavigationRoutes.Greetings.route) {
            GreetingsScreen(navController = navController)
        }
        composable(NavigationRoutes.LaunchScreen.route) {
            LaunchScreen(
                navController = navController,
                authViewModel = hiltViewModel()
            )
        }
        composable(NavigationRoutes.RegistrationPasswordScreen.route) {
            RegistrationPasswordScreen(
                userAuthViewModel = registrationViewModel,
                navController = navController
            )
        }
        composable(NavigationRoutes.Registration.route) {
            RegistrationScreen(
                userAuthViewModel = registrationViewModel,
                navController = navController
            )
        }
        composable(NavigationRoutes.Login.route) {
            LoginScreen(
                loginViewModel = hiltViewModel(),
                navController = navController
            )
        }
    }
}



