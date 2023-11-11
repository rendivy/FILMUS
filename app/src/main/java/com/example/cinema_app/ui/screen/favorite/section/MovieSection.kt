package com.example.cinema_app.ui.screen.favorite.section

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cinema_app.common.Constants
import com.example.cinema_app.presentation.state.FavouriteState
import com.example.cinema_app.ui.FilmCard
import com.example.cinema_app.ui.LargeFilmCard
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.padding15
import com.example.cinema_app.ui.theme.padding20

@Composable
fun MovieSection(movieState: FavouriteState, padding: PaddingValues, navController: NavController) {
    val movies = (movieState as FavouriteState.Content).movie
    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(Gray900),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(movies.chunked(Constants.MAX_FILM_IN_GROUP)) { movieGroup ->
                Row(
                    modifier = Modifier
                        .padding(
                            start = padding15,
                            top = padding20,
                            bottom = padding20,
                            end = padding15
                        ),
                ) {
                    for (i in 0 until 2) {
                        if (i == 1) {
                            Spacer(modifier = Modifier.width(15.dp))
                        }
                        if (i < movieGroup.size) {
                            FilmCard(
                                path = movieGroup[i].poster,
                                movieName = movieGroup[i].name,
                                modifier = Modifier.weight(1f),
                                movieId = movieGroup[i].id,
                                userRating = movieGroup[i].userReview,
                                navController = navController
                            )
                            Log.d("FilmCard", "FilmCard: ${movieGroup[i].userReview}")
                        }

                    }
                }
                if (movieGroup.size > 2) {
                    LargeFilmCard(
                        path = movieGroup[2].poster,
                        userRating = movieGroup[2].userReview,
                        movieName = movieGroup[2].name,
                        movieId = movieGroup[2].id,
                        navController = navController
                    )
                }
            }
        }
}


