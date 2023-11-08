package com.example.cinema_app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.TitleMedium


@Composable
fun FilmCard(path: String, movieName: String, width: Dp, ) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            model = path,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(240.dp)
                .width(width = width)
                .clip(RoundedCornerShape(8.dp)),
            contentDescription = "Film Card"
        ) {
            when (painter.state) {
                is AsyncImagePainter.State.Loading -> {
                    Box(
                        modifier = Modifier
                            .height(240.dp)
                            .width(width = width)
                            .clip(RoundedCornerShape(8.dp))
                            .shimmerEffect()
                    )
                }

                else -> {
                    SubcomposeAsyncImageContent()
                }
            }
        }
        Text(
            text = movieName,
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 5.dp)
                .width(160.dp)
        )
    }

}


@Composable
fun LargeFilmCard(path: String, movieName: String) {
    Column(
        modifier = Modifier.padding(start = 15.dp, top = 20.dp, bottom = 20.dp, end = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box{
            SubcomposeAsyncImage(
                model = path,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentDescription = "Film Card"
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> {
                        Box(
                            modifier = Modifier
                                .height(240.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .shimmerEffect()
                        )
                    }

                    else -> {
                        SubcomposeAsyncImageContent()
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