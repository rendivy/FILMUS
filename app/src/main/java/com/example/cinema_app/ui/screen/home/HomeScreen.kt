package com.example.cinema_app.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.cinema_app.presentation.HomeViewModel
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray400
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SemiBoldStyle
import com.example.cinema_app.ui.theme.TransparentWhite
import com.example.cinema_app.ui.theme.padding128

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navController: NavController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val moviesPaging = homeViewModel.moviePagingFlow.collectAsLazyPagingItems()
    val cardHeight = (screenHeight * 0.9f).coerceAtMost(497.dp)

    when (moviesPaging.loadState.refresh) {

        is LoadState.Error -> {
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

        is LoadState.Loading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray900)
                    .verticalScroll(rememberScrollState()),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(cardHeight)
                        .shimmerEffect(),
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                    text = "Каталог",
                    textAlign = TextAlign.Start,
                    style = SemiBoldStyle,
                    fontSize = 24.sp,
                )
                repeat(2) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, bottom = 16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .height(130.dp)
                                .width(95.dp)
                                .shimmerEffect(),
                        )
                        Box(
                            modifier = Modifier
                                .height(130.dp)
                                .fillMaxWidth()
                                .padding(start = 10.dp)
                                .shimmerEffect()
                        )
                    }
                }
            }
        }

        is LoadState.NotLoading -> {
            val pagerState = rememberPagerState(pageCount = { 4 })
            if (moviesPaging.itemCount > 0) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Gray900)
                        .verticalScroll(rememberScrollState()),
                ) {
                    val startIndex = 4
                    Box(
                        contentAlignment = Alignment.BottomCenter,
                    ) {
                        HorizontalPager(
                            state = pagerState,
                        ) { page ->

                            SubcomposeAsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(onClick = {
                                        navController.navigate("movieDetails/${moviesPaging[page]?.id}/${moviesPaging[page]?.filmRating.toString()}")
                                    })
                                    .height(cardHeight),
                                model = moviesPaging[page]?.poster ?: "",
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            ) {
                                when (val state = painter.state) {
                                    is AsyncImagePainter.State.Loading -> {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .shimmerEffect()
                                                .height(cardHeight)
                                                .clip(RoundedCornerShape(5.dp))
                                        )
                                    }

                                    else -> {
                                        SubcomposeAsyncImageContent()
                                    }
                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .height(24.dp)
                                .width(72.dp)
                                .background(
                                    color = TransparentWhite,
                                    shape = RoundedCornerShape(28.dp)
                                )
                                .align(Alignment.BottomCenter),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(4) { page ->
                                val color =
                                    if (page == pagerState.currentPage) Color.White else Color.Transparent
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .width(10.dp)
                                        .height(10.dp)
                                        .background(color = color)
                                        .border(width = 2.dp, color = Color.White)

                                ) {
                                }
                            }
                        }
                    }
                    Text(
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                        text = "Каталог",
                        textAlign = TextAlign.Start,
                        style = SemiBoldStyle,
                        fontSize = 24.sp,
                    )
                    FilmColumn(
                        moviesPaging = moviesPaging,
                        startIndex = startIndex,
                        navController = navController
                    )

                }
            }
        }

        else -> {}
    }
}

