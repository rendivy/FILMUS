package com.example.cinema_app.ui.screen.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cinema_app.presentation.FavouritesMovieViewModel
import com.example.cinema_app.presentation.state.FavouriteState
import com.example.cinema_app.ui.FilmCard
import com.example.cinema_app.ui.LargeFilmCard
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.InternBoldLarge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(favouritesMovieViewModel: FavouritesMovieViewModel) {
    val movieState by favouritesMovieViewModel.favouriteMovieState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        favouritesMovieViewModel.getFavouriteMovie()
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Любимое", style = InternBoldLarge, color = Color.White
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Gray900,
                scrolledContainerColor = Gray900,
                navigationIconContentColor = Color.White,
                titleContentColor = Accent
            ),
        )
    }) {
        when (movieState) {
            is FavouriteState.Loading -> {
                Text(text = "Loading")
            }

            is FavouriteState.NoFavourite -> {
                Text(text = "No favourite")
            }

            is FavouriteState.Content -> {
                val movies = (movieState as FavouriteState.Content).movie.movies
                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                        .background(Gray900),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    items(movies.chunked(3)) { movieGroup ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp, top = 20.dp, bottom = 20.dp, end = 15.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            for (i in 0 until 2) {
                                if (i < movieGroup.size) {
                                    FilmCard(path = movieGroup[i].poster, movieName = movieGroup[i].name)
                                }
                            }
                        }
                        if (movieGroup.size > 2) {
                            LargeFilmCard(path = movieGroup[2].poster, movieName = movieGroup[2].name)
                        }
                    }
                }
            }

            else -> {}
        }


    }
}