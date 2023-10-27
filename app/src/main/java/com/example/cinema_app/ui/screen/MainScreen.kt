package com.example.cinema_app.ui.screen

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinema_app.ui.navigation.NavigationRoutes
import com.example.cinema_app.ui.screen.component.BottomBar
import com.example.cinema_app.ui.screen.favorite.FavouriteScreen
import com.example.cinema_app.ui.screen.home.HomeScreen
import com.example.cinema_app.ui.screen.profile.ProfileScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.Home.route
        ) {
            composable(NavigationRoutes.Profile.route) {
                ProfileScreen()
            }
            composable(NavigationRoutes.Home.route) {
                HomeScreen()
            }
            composable(NavigationRoutes.Favourite.route) {
                FavouriteScreen()
            }
        }
    }
}