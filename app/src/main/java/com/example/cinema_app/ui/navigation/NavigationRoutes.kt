package com.example.cinema_app.ui.navigation

import com.example.cinema_app.common.Constants

sealed class NavigationRoutes(val route: String) {
    data object Greetings : NavigationRoutes(Constants.GREETINGS_ROUTE)
    data object Registration : NavigationRoutes(Constants.REGISTRATION_ROUTE)
    data object Login: NavigationRoutes(Constants.LOGIN_ROUTE)
}
