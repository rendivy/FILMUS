package com.example.cinema_app.ui.screen.favorite.section

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinema_app.presentation.entities.FavouriteMovieUi
import com.example.cinema_app.presentation.state.FavouriteState
import com.example.cinema_app.ui.screen.favorite.FilmCard
import com.example.cinema_app.ui.screen.favorite.LargeFilmCard
import com.example.cinema_app.ui.screen.favorite.testTags.FavouriteScreenTags
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.padding15
import com.example.cinema_app.ui.theme.padding20
import com.example.feature_filmus_movie_impl.data.Constants

@Composable
fun FavouriteScreenSection(
    movieState: FavouriteState.Content,
    padding: PaddingValues,
    onCardClick: (String) -> Unit,
) {
    val movies = movieState.movie
    val firstIndex = 1
    val secondIndex = 2
    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(Gray900)
            .testTag(FavouriteScreenTags.FavouriteLazyColumnTag),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(movies.chunked(Constants.MAX_FILM_IN_GROUP), key = { it.size }) { movieGroup ->
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
                    if (i == firstIndex) {
                        Spacer(modifier = Modifier.width(padding15))
                    }
                    if (i < movieGroup.size) {
                        FilmCard(
                            path = movieGroup[i].poster,
                            movieName = movieGroup[i].name,
                            modifier = Modifier.weight(1f),
                            userRating = movieGroup[i].userReview,
                            movieId = movieGroup[i].id,
                            onCardClick = onCardClick,
                        )
                    }

                }
            }
            if (movieGroup.size > secondIndex) {
                LargeFilmCard(
                    path = movieGroup[secondIndex].poster,
                    userRating = movieGroup[secondIndex].userReview,
                    movieName = movieGroup[secondIndex].name,
                    movieId = movieGroup[secondIndex].id,
                    onCardClick = onCardClick,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouriteScreenSectionPreview() {
    val sampleMovies = listOf(
        FavouriteMovieUi(
            id = "1",
            name = "Inception",
            poster = "https://example.com/inception.jpg",
            userReview = 9
        ),
        FavouriteMovieUi(
            id = "2",
            name = "The Matrix",
            poster = "https://example.com/matrix.jpg",
            userReview = 8
        ),
        FavouriteMovieUi(
            id = "3",
            name = "Parasite",
            poster = "https://example.com/parasite.jpg",
            userReview = 10
        ),
        FavouriteMovieUi(
            id = "4",
            name = "Interstellar",
            poster = "https://example.com/interstellar.jpg",
            userReview = 9
        )
    )

    val movieState = FavouriteState.Content(movie = sampleMovies)

    FavouriteScreenSection(
        movieState = movieState,
        padding = PaddingValues(16.dp), // Adjust padding as needed
        onCardClick = { movieId -> println("Clicked on movie ID: $movieId") }
    )
}


