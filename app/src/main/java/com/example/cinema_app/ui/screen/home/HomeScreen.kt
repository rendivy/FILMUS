package com.example.cinema_app.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.cinema_app.presentation.HomeViewModel
import com.example.cinema_app.presentation.state.HomeState
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray400
import com.example.cinema_app.ui.theme.InternBoldLarge
import com.example.cinema_app.ui.theme.padding128

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val sliderList by homeViewModel.movieState.collectAsStateWithLifecycle()
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val cardHeight = (screenHeight * 0.9f).coerceAtMost(600.dp)

    when (sliderList) {
        is HomeState.Initial -> {
            LaunchedEffect(Unit) {
                homeViewModel.getMovies()
            }
        }

        is HomeState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(visible = true) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(padding128),
                        color = Accent,
                        trackColor = Gray400
                    )
                }
            }
        }

        is HomeState.Content -> {
            val movies = (sliderList as HomeState.Content).movie.movies
            val pagerState = rememberPagerState(pageCount = { movies.size })

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                HorizontalPager(
                    state = pagerState,
                    pageSize = PageSize.Fill,
                ) { page ->
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(cardHeight),
                        model = movies[page].poster,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        is HomeState.Error -> {
            val exception = (sliderList as HomeState.Error).exception
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = exception, style = InternBoldLarge, color = Color.White)
            }
        }


    }
}
