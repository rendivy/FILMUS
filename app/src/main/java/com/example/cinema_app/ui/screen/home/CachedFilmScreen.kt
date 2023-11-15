package com.example.cinema_app.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cinema_app.R
import com.example.cinema_app.presentation.HomeViewModel
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.BadRatingColor
import com.example.cinema_app.ui.theme.CardTitle
import com.example.cinema_app.ui.theme.GoodRatingColor
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.MediumRatingColor
import com.example.cinema_app.ui.theme.SecondarySemiBoldStyle
import com.example.cinema_app.ui.theme.SemiBadRatingColor
import com.example.cinema_app.ui.theme.SemiBoldStyle
import com.example.cinema_app.ui.theme.SemiMediumRatingColor
import com.example.cinema_app.ui.theme.TitleMedium
import com.example.cinema_app.ui.theme.padding10
import com.example.cinema_app.ui.theme.mediumPadding


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CachedFilmScreen(homeViewModel: HomeViewModel, onClick: () -> Unit) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val moviesPaging = homeViewModel.movieState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val cardHeight = (screenHeight * 0.9f).coerceAtMost(497.dp)
    LaunchedEffect(Unit) {
        homeViewModel.getAllCachedFilms()
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900),
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight)
                    .shimmerEffect()
            )
        }
        item{
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = mediumPadding,
                        end = mediumPadding,
                        top = mediumPadding,
                        bottom = mediumPadding
                    ),
                onClick = onClick,
                shape = RoundedCornerShape(size = padding10),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Accent,
                    disabledContainerColor = Accent
                ),
                contentPadding = PaddingValues(12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.repeat),
                    style = SecondarySemiBoldStyle
                )
            }
        }
        item{
            Text(
                modifier = Modifier.padding(top = mediumPadding, start = mediumPadding),
                text = stringResource(id = R.string.magazine_label),
                textAlign = TextAlign.Start,
                style = SemiBoldStyle,
                fontSize = 24.sp,
            )
        }
        items(moviesPaging.value.size) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Gray900)
                    .clip(RoundedCornerShape(5.dp))
                    .padding(mediumPadding)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier
                        .height(130.dp)
                        .width(95.dp)
                        .shimmerEffect()
                        .clickable {})
                    Spacer(modifier = Modifier.width(padding10))
                    Column {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = moviesPaging.value[it].filmName,
                                style = CardTitle,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .fillMaxWidth(0.8f)
                            )
                            if (moviesPaging.value[it].userRating != null) {
                                val color = when (moviesPaging.value[it].userRating) {
                                    in 0..2 -> BadRatingColor
                                    in 2..4 -> SemiBadRatingColor
                                    in 4..6 -> SemiMediumRatingColor
                                    in 6..8 -> MediumRatingColor
                                    in 8..10 -> GoodRatingColor
                                    else -> Color.White
                                }
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(35.dp))
                                        .background(color),
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.star_3),
                                            contentDescription = "star rating",
                                            modifier = Modifier
                                                .padding(
                                                    start = 4.dp,
                                                    end = 4.dp,
                                                    top = 4.dp,
                                                    bottom = 4.dp
                                                )
                                                .width(mediumPadding)
                                                .height(mediumPadding),
                                        )
                                        Text(
                                            modifier = Modifier.padding(
                                                end = 4.dp,
                                                top = 4.dp,
                                                bottom = 4.dp
                                            ),
                                            text = "${moviesPaging.value[it].userRating}",
                                            style = TitleMedium,
                                            color = Color.White,
                                            fontSize = 16.sp,
                                        )
                                    }

                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row() {
                            Text(
                                text = moviesPaging.value[it].filmCountry,
                                style = TitleMedium,
                                color = Color.White,
                                fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp)
                            )
                            Text(
                                text = "·",
                                style = TitleMedium,
                                color = Color.White,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                            )

                            Text(
                                text = moviesPaging.value[it].filmYear.toString(),
                                style = TitleMedium,
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        FlowRow(
                            verticalArrangement = Arrangement.Center,
                        ) {
                            moviesPaging.value[it].filmGenres.forEach {
                                GenreTag(values = it)
                            }
                        }
                    }
                }
            }
        }
    }
}