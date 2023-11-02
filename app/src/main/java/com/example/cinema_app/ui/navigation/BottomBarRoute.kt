package com.example.cinema_app.ui.navigation

import com.example.cinema_app.R
import com.example.cinema_app.common.Constants

sealed class BottomBarRoute(
    val route: String,
    val icon: Int,
    val title: String
) {
    data object Home : BottomBarRoute(
        route = NavigationRoutes.Home.route,
        icon = R.drawable.home_icon,
        title = Constants.BOTTOM_BAR_HOME_TITLE
    )

    data object Profile : BottomBarRoute(
        route = NavigationRoutes.Profile.route,
        icon = R.drawable.profile_icon,
        title = Constants.BOTTOM_BAR_PROFILE_TITLE
    )

    data object Favourite : BottomBarRoute(
        route = NavigationRoutes.Favourite.route,
        icon = R.drawable.favourite_icon,
        title = Constants.BOTTOM_BAR_FAVOURITE_TITLE
    )
}