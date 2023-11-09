package com.example.cinema_app.ui.screen.details

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cinema_app.R
import com.example.cinema_app.domain.entity.DetailsDTO
import com.example.cinema_app.ui.navigation.NavigationRoutes
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.TitleLarge


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    navController: androidx.navigation.NavController
) {
    TopAppBar(
        modifier = Modifier.height(44.dp),
        title = {},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Gray900,
            scrolledContainerColor = Gray900,
            navigationIconContentColor = Color.White,
            titleContentColor = Accent
        ),
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
                navController.navigate(NavigationRoutes.Home.route)
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.back_button_icon),
                    contentDescription = "back_icon_button",
                    modifier = Modifier.size(12.dp),
                    tint = Color.White,
                )
            }
        },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTop(
    navController: NavController,
    content: DetailsDTO
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.height(44.dp),
        title = {
            Text(
                text = content.name,
                modifier = Modifier.padding(top = 10.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp,
                style = TitleLarge,
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Gray900,
            scrolledContainerColor = Gray900,
            navigationIconContentColor = Color.White,
            titleContentColor = Accent
        ),
        actions = {
            IconButton(onClick = {
                navController.popBackStack()
                navController.navigate(NavigationRoutes.Home.route)
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.back_button_icon),
                    contentDescription = "back_icon_button",
                    modifier = Modifier.size(12.dp),
                    tint = Color.White,
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
                navController.navigate(NavigationRoutes.Home.route)
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.back_button_icon),
                    contentDescription = "back_icon_button",
                    modifier = Modifier.size(12.dp),
                    tint = Color.White,
                )
            }
        },
    )
}