package com.example.cinema_app.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
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
fun MainScreen(navHostController: NavHostController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
        content = {
            NavHost(
                navController = navController,
                modifier = Modifier.padding(it),
                startDestination = NavigationRoutes.Home.route
            ) {
                composable(NavigationRoutes.Profile.route) {
                    ProfileScreen(profileViewModel = hiltViewModel())
                }
                composable(NavigationRoutes.Home.route) {
                    HomeScreen(homeViewModel = hiltViewModel())
                }
                composable(NavigationRoutes.Favourite.route) {
                    FavouriteScreen(
                        favouritesMovieViewModel = hiltViewModel(),
                        navHostController = navHostController
                    )
                }
            }
        }
    )
}