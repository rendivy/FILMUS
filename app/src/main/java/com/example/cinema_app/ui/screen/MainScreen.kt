package com.example.cinema_app.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cinema_app.ui.navigation.NavigationRoutes
import com.example.cinema_app.ui.screen.component.BottomBar
import com.example.cinema_app.ui.screen.details.MovieDetailsScreen
import com.example.cinema_app.ui.screen.favorite.FavouriteScreen
import com.example.cinema_app.ui.screen.home.HomeScreen
import com.example.cinema_app.ui.screen.profile.ProfileScreen


@Composable
fun MainScreen(navHostController: NavHostController) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Log.d("MainScreen", "currentRoute: $currentRoute")
    Scaffold(
        bottomBar = {
            if (currentRoute != "movieDetails/{id}/{movieRating}") {
                BottomBar(navController = navController)
            }

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
                    HomeScreen(homeViewModel = hiltViewModel(), navController = navController)
                }
                composable(
                    route = "movieDetails/{id}/{movieRating}",
                    arguments = listOf(navArgument("id") { type = NavType.StringType },
                        navArgument("movieRating") { type = NavType.StringType })
                ) { backStackEntry ->
                    MovieDetailsScreen(
                        backStackEntry.arguments?.getString("id").toString(),
                        backStackEntry.arguments?.getString("movieRating").toString(),
                        movieDetailsViewModel = hiltViewModel()
                    )
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