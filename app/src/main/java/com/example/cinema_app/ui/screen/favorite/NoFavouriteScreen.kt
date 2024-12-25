package com.example.cinema_app.ui.screen.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.cinema_app.R
import com.example.cinema_app.ui.screen.favorite.testTags.FavouriteScreenTags.NoFavouriteScreenTag
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.InternBoldLarge
import com.example.cinema_app.ui.theme.TitleSmall
import com.example.cinema_app.ui.theme.mediumPadding
import com.example.cinema_app.ui.theme.padding100
import com.example.cinema_app.ui.theme.padding5

@Composable
fun NoFavouriteScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Gray900)
            .padding(
                top = padding100,
                bottom = mediumPadding,
                start = mediumPadding,
                end = mediumPadding
            )
            .testTag(NoFavouriteScreenTag),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(id = R.string.no_favourite_label),
            style = InternBoldLarge, color = Color.White, textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(top = padding5),
            text = stringResource(id = R.string.no_favourite_text),
            style = TitleSmall, color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun NoFavouriteScreenPreview() {
    NoFavouriteScreen()
}
