package com.example.cinema_app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarRoute(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    data object Home : BottomBarRoute(
        route = NavigationRoutes.Home.route,
        icon = Icons.Default.Home,
        title = "Home"
    )

    data object Profile : BottomBarRoute(
        route = NavigationRoutes.Profile.route,
        icon = Icons.Default.AccountCircle,
        title = "Profile"
    )

    data object Favourite : BottomBarRoute(
        route = NavigationRoutes.Favourite.route,
        icon = Icons.Default.Favorite,
        title = "Profile"
    )
}