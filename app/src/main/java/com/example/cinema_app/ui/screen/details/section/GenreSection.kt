package com.example.cinema_app.ui.screen.details.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cinema_app.data.entity.GenreX
import com.example.cinema_app.ui.screen.home.GenreTag
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.padding10
import com.example.cinema_app.ui.theme.semiMediumPadding
import com.example.cinema_app.ui.theme.padding5

@ExperimentalLayoutApi
@Composable
fun GenreSection(data: List<GenreX>) {
    FlowRow(
        modifier = Modifier
            .padding(start = semiMediumPadding)
            .fillMaxWidth()
            .background(Gray900)
    ) {
        data.forEach {
            GenreTag(it.name, Accent, tinyPadding = padding5, mediumPadding = padding10)
        }
    }

}