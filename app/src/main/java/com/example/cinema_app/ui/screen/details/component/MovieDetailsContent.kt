package com.example.cinema_app.ui.screen.details.component

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cinema_app.domain.entity.DetailsDTO
import com.example.cinema_app.presentation.MovieDetailsViewModel
import com.example.cinema_app.ui.screen.details.DetailsPoster
import com.example.cinema_app.ui.screen.details.DetailsTop
import com.example.cinema_app.ui.screen.details.DetailsTopBar
import com.example.cinema_app.ui.screen.details.GenreHeadline
import com.example.cinema_app.ui.screen.details.MovieHeadline
import com.example.cinema_app.ui.screen.details.ReviewHeadlines
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.padding16

@ExperimentalLayoutApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun MovieDetailsContent(
    content: DetailsDTO,
    filmRating: String,
    movieDetailsViewModel: MovieDetailsViewModel,
    navController: NavController
) {
    val lazyListState = rememberLazyListState()
    val reviewDialogOpen = remember { mutableStateOf(false) }
    val scrollState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 2 } }

    Scaffold(
        topBar = {

            DetailsTopBar(navController = navController)

        },
        content = {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(color = Gray900),
                lazyListState,
            ) {
                item {
                    DetailsPoster(content = content, lazyListState = lazyListState)
                }
                item {
                    MovieHeadline(content = content)
                }
                if (content.description != "-") {
                    item {
                        ExpandedText(
                            text = content.description,
                            color = Color.White,
                            modifier = Modifier.padding(
                                start = padding16,
                                end = padding16,
                                bottom = 10.dp,
                                top = padding16
                            )
                        )
                    }
                }
                item {
                    GenreHeadline(content)
                }
                item {
                    ReviewHeadlines(
                        content = content,
                        movieDetailsViewModel = movieDetailsViewModel,
                        reviewDialogOpen = reviewDialogOpen
                    )
                }
            }

        })
    if (scrollState.value) {
        AnimatedVisibility(scrollState.value) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                DetailsTop(navController = navController, content = content)
            }
        }
    }
}

























