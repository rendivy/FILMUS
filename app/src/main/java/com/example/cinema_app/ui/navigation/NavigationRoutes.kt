package com.example.cinema_app.ui.navigation

import com.example.cinema_app.common.NavigationConstant

sealed class NavigationRoutes(val route: String) {
    data object Greetings : NavigationRoutes(NavigationConstant.GREETINGS_ROUTE)
    data object Profile: NavigationRoutes(NavigationConstant.PROFILE_ROUTE)
    data object Main: NavigationRoutes(NavigationConstant.MAIN_ROUTE)
    data object Favourite: NavigationRoutes(NavigationConstant.FAVORITES_ROUTE)
    data object Registration : NavigationRoutes(NavigationConstant.REGISTRATION_ROUTE)
    data object Login : NavigationRoutes(NavigationConstant.LOGIN_ROUTE)
    data object RegistrationPasswordScreen : NavigationRoutes(NavigationConstant.REGISTRATION_PASSWORD_ROUTE)
    data object Home: NavigationRoutes(NavigationConstant.HOME_ROUTE)
    data object LaunchScreen : NavigationRoutes(NavigationConstant.SPLASH_SCREEN_ROUTE)
}
