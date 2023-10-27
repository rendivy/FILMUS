package com.example.cinema_app.ui.screen.favorite.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.cinema_app.common.Constants
import com.example.cinema_app.presentation.state.FavouriteState
import com.example.cinema_app.ui.FilmCard
import com.example.cinema_app.ui.LargeFilmCard
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.maxFavouriteCardSize
import com.example.cinema_app.ui.theme.padding15
import com.example.cinema_app.ui.theme.padding20

@Composable
fun MovieSection(movieState: FavouriteState, padding: PaddingValues) {
    val movies = (movieState as FavouriteState.Content).movie.movies
    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(Gray900),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(movies.chunked(Constants.MAX_FILM_IN_GROUP)) { movieGroup ->
            val screenWidth = LocalConfiguration.current.screenWidthDp.dp
            val cardWidth = (screenWidth * 0.5f).coerceAtMost(maxFavouriteCardSize)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = padding15,
                        top = padding20,
                        bottom = padding20,
                        end = padding15
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                for (i in 0 until 2) {
                    if (i < movieGroup.size) {
                        FilmCard(
                            path = movieGroup[i].poster,
                            movieName = movieGroup[i].name,
                            width = cardWidth
                        )
                    }
                }
            }
            if (movieGroup.size > 2) {
                LargeFilmCard(
                    path = movieGroup[2].poster,
                    movieName = movieGroup[2].name
                )
            }
        }
    }
}