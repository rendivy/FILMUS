package com.example.cinema_app.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.cinema_app.R
import com.example.cinema_app.data.entity.Genre
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.BadRatingColor
import com.example.cinema_app.ui.theme.CardTitle
import com.example.cinema_app.ui.theme.GoodRatingColor
import com.example.cinema_app.ui.theme.Gray750
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.MediumRatingColor
import com.example.cinema_app.ui.theme.SemiBadRatingColor
import com.example.cinema_app.ui.theme.SemiBoldStyle
import com.example.cinema_app.ui.theme.SemiGoodRatingColor
import com.example.cinema_app.ui.theme.SemiMediumRatingColor
import com.example.cinema_app.ui.theme.ShortSpace
import com.example.cinema_app.ui.theme.TitleMedium
import com.example.cinema_app.ui.theme.padding10
import com.example.cinema_app.ui.theme.padding16
import com.example.cinema_app.ui.theme.padding8

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeFilmCard(
    modifier: Modifier = Modifier,
    filmTitle: String,
    filmYear: String,
    filmCountry: String,
    filmPoster: String,
    filmGenres: List<Genre>,
    filmRating: Double,
    userRating: Int?
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Gray900)
            .padding(padding16)
    ) {
        val filmRatingColor = when (filmRating) {
            in 0.1..2.0 -> BadRatingColor
            in 2.0..3.0 -> BadRatingColor
            in 3.0..4.0 -> SemiBadRatingColor
            in 4.0..5.0 -> SemiMediumRatingColor
            in 5.0..6.0 -> MediumRatingColor
            in 6.0..8.0 -> MediumRatingColor
            in 8.0..8.9 -> SemiGoodRatingColor
            in 9.0..10.0 -> GoodRatingColor
            else -> Color.White
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(contentAlignment = Alignment.BottomCenter) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .height(130.dp)
                        .width(95.dp),
                    model = filmPoster,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                ) {
                    val state = painter.state
                    if (state is AsyncImagePainter.State.Loading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shimmerEffect()
                                .height(130.dp)
                                .clip(RoundedCornerShape(5.dp))
                        )
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }
                if (filmRating >= 0.0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .width(37.dp)
                            .height(20.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(filmRatingColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = filmRating.toString(),
                            style = SemiBoldStyle,
                            color = Color.Black,
                            fontSize = 13.sp,
                        )
                    }
                }

            }


            Spacer(modifier = Modifier.width(padding10))
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = filmTitle,
                        style = CardTitle,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = ShortSpace)
                            .fillMaxWidth(0.8f)
                    )
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
                                            start = ShortSpace,
                                            end = ShortSpace,
                                            top = ShortSpace,
                                            bottom = ShortSpace
                                        )
                                        .width(padding16)
                                        .height(padding16),
                                )
                                Text(
                                    modifier = Modifier.padding(
                                        end = ShortSpace,
                                        top = ShortSpace,
                                        bottom = ShortSpace
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
                Spacer(modifier = Modifier.height(ShortSpace))
                Row() {
                    Text(
                        text = filmCountry,
                        style = TitleMedium,
                        color = Color.White,
                        fontSize = 12.sp, modifier = Modifier.padding(start = ShortSpace)
                    )
                    Text(
                        text = "·", style = TitleMedium,
                        color = Color.White,
                        fontSize = 12.sp, modifier = Modifier.padding(start = ShortSpace, end = ShortSpace)
                    )

                    Text(
                        text = filmYear,
                        style = TitleMedium,
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                FlowRow(
                    verticalArrangement = Arrangement.Center,
                ) {
                    filmGenres.forEach { genreName ->
                        GenreTag(genreName.name)
                    }
                }
            }
        }
    }
}


@Composable
fun RatingBox(ratingValue: String) {
    val containerColor =
        when (ratingValue.toDouble()) {
            in 0.1..2.0 -> BadRatingColor
            in 2.0..3.0 -> BadRatingColor
            in 3.0..4.0 -> SemiBadRatingColor
            in 4.0..5.0 -> SemiMediumRatingColor
            in 5.0..6.0 -> SemiMediumRatingColor
            in 6.0..8.0 -> MediumRatingColor
            in 8.0..8.9 -> SemiGoodRatingColor
            in 9.0..10.0 -> GoodRatingColor
            else -> Color.White
        }
    val textColor =
        when (ratingValue.toDouble()) {
            in 0.1..2.0 -> Color.White
            in 2.0..3.0 -> Color.White
            in 3.0..4.0 -> Color.White
            in 4.0..5.0 -> Color.White
            in 5.0..6.0 -> Color.White
            in 6.0..8.0 -> Color.Black
            in 8.0..8.9 -> Color.White
            in 9.0..10.0 -> Color.White
            else -> Color.White
        }
    Box(
        modifier = Modifier
            .width(51.dp)
            .height(26.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(containerColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = ratingValue,
            style = SemiBoldStyle,
            color = textColor,
            fontSize = 15.sp,
        )
    }
}


@Composable
fun GenreTag(
    values: String = "драма",
    backgroundColor: Color = Gray750,
    style: TextStyle = TitleMedium,
    tinyPadding: Dp = 2.dp,
    mediumPadding: Dp = padding8
) {
    Box(
        modifier = Modifier
            .padding(start = ShortSpace, end = ShortSpace, bottom = ShortSpace, top = ShortSpace)
            .background(color = backgroundColor, shape = RoundedCornerShape(5.dp))

    ) {
        Text(
            modifier = Modifier.padding(
                top = tinyPadding,
                bottom = tinyPadding,
                start = mediumPadding,
                end = mediumPadding
            ),
            text = values, style = style, color = Color.White, textAlign = TextAlign.Center
        )
    }
}