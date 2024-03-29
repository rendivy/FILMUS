package com.example.cinema_app.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.cinema_app.domain.entity.FilmDTO
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.semiMediumPadding
import com.example.cinema_app.ui.theme.padding24


@Composable
fun FilmColumn(
    moviesPaging: LazyPagingItems<FilmDTO>,
    startIndex: Int = 0,
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val itemCount = moviesPaging.itemCount
        for (index in startIndex until itemCount) {
            val item = moviesPaging[index]
            item?.let {
                HomeFilmCard(
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate("movieDetails/${it.id}/${it.filmRating}")
                    }),
                    filmTitle = it.name,
                    filmYear = it.year.toString(),
                    filmCountry = it.country,
                    filmPoster = it.poster,
                    filmGenres = it.genres,
                    filmRating = it.filmRating,
                    userRating = it.userReview
                )
            }
        }
        if (moviesPaging.loadState.append == LoadState.Loading) {
            AnimatedVisibility(visible = true) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = semiMediumPadding)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(padding24),
                        color = Accent,
                        trackColor = Gray900
                    )
                }
            }
        }

    }
}
