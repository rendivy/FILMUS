package com.example.cinema_app.ui.screen.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.cinema_app.R
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.BadRatingColor
import com.example.cinema_app.ui.theme.GoodRatingColor
import com.example.cinema_app.ui.theme.MediumRatingColor
import com.example.cinema_app.ui.theme.SemiBadRatingColor
import com.example.cinema_app.ui.theme.SemiMediumRatingColor
import com.example.cinema_app.ui.theme.TitleMedium
import com.example.cinema_app.ui.theme.mediumPadding
import com.example.cinema_app.ui.theme.padding15
import com.example.cinema_app.ui.theme.padding20
import com.example.cinema_app.ui.theme.shortPadding
import com.example.cinema_app.ui.theme.tinyPadding


@Composable
fun FilmCard(
    path: String,
    movieName: String,
    modifier: Modifier,
    movieId: String,
    navController: NavController,
    userRating: Int? = null,
) {
    Column(
        modifier = modifier,
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .height(240.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .clickable {
                        navController.navigate("movieDetails/${movieId}/'")
                    },
                model = path,
                contentScale = ContentScale.Crop,
                contentDescription = "Film Card"
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> {
                        Box(
                            modifier = Modifier
                                .height(240.dp)
                                .width(width = 200.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .shimmerEffect()
                        )
                    }

                    else -> {
                        SubcomposeAsyncImageContent()
                    }
                }
            }
            if (userRating != null) {
                val color = when (userRating) {
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
                        .background(color)
                        .padding(top = 2.dp, end = 2.dp),
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
                                    start = tinyPadding,
                                    end = tinyPadding,
                                    top = tinyPadding,
                                    bottom = tinyPadding
                                )
                                .width(mediumPadding)
                                .height(mediumPadding),
                        )
                        Text(
                            modifier = Modifier.padding(
                                end = tinyPadding,
                                top = tinyPadding,
                                bottom = tinyPadding
                            ),
                            text = "${userRating}",
                            style = TitleMedium,
                            color = Color.White,
                            fontSize = 16.sp,
                        )
                    }

                }
            }

        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = movieName,
            style = TitleMedium,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
        )
    }

}


@Composable
fun LargeFilmCard(
    path: String,
    movieName: String,
    userRating: Int?,
    movieId: String,
    navController: NavController
) {
    Column(
        modifier = Modifier.padding(start = padding15, top = padding20, bottom = padding20, end = padding15),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = TopEnd) {
            SubcomposeAsyncImage(
                model = path,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clickable {
                        navController.navigate("movieDetails/${movieId}/${6.2}")
                    }
                    .clip(RoundedCornerShape(shortPadding)),
                contentDescription = "Film Card"
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> {
                        Box(
                            modifier = Modifier
                                .height(240.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(shortPadding))
                                .shimmerEffect()
                        )
                    }

                    else -> {
                        SubcomposeAsyncImageContent()
                    }
                }
            }
            if (userRating != null) {
                val color = when (userRating) {
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
                        .background(color)
                        .padding(top = 2.dp, end = 2.dp),
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
                                    start = tinyPadding,
                                    end = tinyPadding,
                                    top = tinyPadding,
                                    bottom = tinyPadding
                                )
                                .width(mediumPadding)
                                .height(mediumPadding),
                        )
                        Text(
                            modifier = Modifier.padding(
                                end = tinyPadding,
                                top = tinyPadding,
                                bottom = tinyPadding
                            ),
                            text = "${userRating}",
                            style = TitleMedium,
                            color = Color.White,
                            fontSize = 16.sp,
                        )
                    }

                }
            }
        }
        Text(
            text = movieName,
            style = TitleMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
        )
    }

}