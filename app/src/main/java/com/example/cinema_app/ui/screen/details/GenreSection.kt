package com.example.cinema_app.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cinema_app.domain.entity.DetailsDTO
import com.example.cinema_app.ui.screen.details.section.FilmDetailsSection
import com.example.cinema_app.ui.screen.details.section.GenreSection
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SemiBoldStyle
import com.example.cinema_app.ui.theme.padding16

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenreHeadline(content: DetailsDTO) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray900),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Жанры",
            modifier = Modifier
                .background(color = Gray900)
                .padding(start = padding16, end = padding16, bottom = 10.dp),
            style = SemiBoldStyle
        )
        GenreSection(content.genres)
        Spacer(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth()
                .background(Gray900)
        )
        FilmDetailsSection(content)
    }
}