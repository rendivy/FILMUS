package com.example.cinema_app.ui.navigation

import com.example.cinema_app.common.NavigationConstant

sealed class NavigationRoutes(val route: String) {
    data object Greetings : NavigationRoutes(NavigationConstant.GREETINGS_ROUTE)
    data object Registration : NavigationRoutes(NavigationConstant.REGISTRATION_ROUTE)
    data object Login: NavigationRoutes(NavigationConstant.LOGIN_ROUTE)
    data object LaunchScreen: NavigationRoutes(NavigationConstant.SPLASH_SCREEN_ROUTE)
}
