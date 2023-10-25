package com.example.cinema_app.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cinema_app.ui.navigation.BottomBarRoute
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Black22
import com.example.cinema_app.ui.theme.BottomBarTitle
import com.example.cinema_app.ui.theme.Gray400

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarRoute.Home,
        BottomBarRoute.Favourite,
        BottomBarRoute.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar(
        modifier = Modifier.background(color = Black22),
        containerColor = Black22,
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarRoute,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title, style = BottomBarTitle)
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(screen.icon),
                contentDescription = "Navigation icon",
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        colors = NavigationBarItemDefaults.colors(
            unselectedIconColor = Gray400,
            selectedIconColor = Accent,
            unselectedTextColor = Gray400,
            selectedTextColor = Accent
        ),
        onClick = {
            if (navController.currentDestination?.route != screen.route) {
                navController.navigate(screen.route)
            }
        })
}
