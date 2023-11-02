package com.example.cinema_app.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.paging.compose.LazyPagingItems
import com.example.cinema_app.domain.entity.FilmDto


@Composable
fun FilmColumn(moviesPaging: LazyPagingItems<FilmDto>, startIndex: Int = 0) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val itemCount = moviesPaging.itemCount
        for (index in startIndex until itemCount) {
            val item = moviesPaging[index]
            item?.let {
                HomeFilmCard(
                    filmTitle = it.name,
                    filmYear = it.year.toString(),
                    filmCountry = it.country,
                    filmPoster = it.poster,
                    filmGenres = it.genres,
                    filmRating = it.filmRating,
                    userRating = it.userRating
                )
            }
        }
    }
}
