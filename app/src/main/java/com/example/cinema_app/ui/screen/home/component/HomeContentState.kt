package com.example.cinema_app.ui.screen.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.cinema_app.R
import com.example.cinema_app.domain.entity.FilmDTO
import com.example.cinema_app.ui.screen.home.FilmColumn
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SemiBoldStyle
import com.example.cinema_app.ui.theme.TransparentWhite
import com.example.cinema_app.ui.theme.padding10
import com.example.cinema_app.ui.theme.padding16
import com.example.cinema_app.ui.theme.padding5

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContentState(
    navController: NavHostController,
    moviesPaging: LazyPagingItems<FilmDTO>,
    cardHeight: Dp
) {
    val pagerState = rememberPagerState(pageCount = { 4 })
    if (moviesPaging.itemCount > 0) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray900),
        ) {
            item {
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
                            when (painter.state) {
                                is AsyncImagePainter.State.Loading -> {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .shimmerEffect()
                                            .height(cardHeight)
                                            .clip(RoundedCornerShape(padding5))
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
                            .padding(bottom = padding10)
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
                                    .width(padding10)
                                    .height(padding10)
                                    .background(color = color)
                                    .border(width = 2.dp, color = Color.White)

                            ) {


                            }
                        }
                    }
                }
                Text(
                    modifier = Modifier.padding(top = padding16, start = padding16),
                    text = stringResource(id = R.string.magazine_label),
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
}




