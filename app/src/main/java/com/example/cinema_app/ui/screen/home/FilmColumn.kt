package com.example.cinema_app.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.paging.compose.LazyPagingItems
import com.example.cinema_app.data.entity.Film


@Composable
fun FilmColumn(moviesPaging: LazyPagingItems<Film>) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items((moviesPaging.itemCount + 4) / 6) { groupIndex ->
            Column {
                val startIndex = groupIndex * 6
                val endIndex = minOf(startIndex + 6, moviesPaging.itemCount)
                val startIndexOffset = if (groupIndex == 0) maxOf(0, endIndex - 2) else startIndex

                for (index in startIndexOffset until endIndex) {
                    val item = moviesPaging[index]
                    item?.let {
                        HomeFilmCard(
                            filmTitle = it.name,
                            filmYear = it.year.toString(),
                            filmCountry = it.country,
                            filmPoster = it.poster,
                            filmGenres = it.genres
                        )
                    }
                }
            }
        }
    }

}