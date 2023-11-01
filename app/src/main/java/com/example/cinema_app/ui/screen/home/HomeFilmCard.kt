package com.example.cinema_app.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cinema_app.data.entity.Genre
import com.example.cinema_app.ui.theme.CardTitle
import com.example.cinema_app.ui.theme.Gray750
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.TitleMedium

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeFilmCard(
    filmTitle: String,
    filmYear: String,
    filmCountry: String,
    filmPoster: String,
    filmGenres: List<Genre>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray900)
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = Modifier
                    .height(130.dp)
                    .width(95.dp)
                    .clip(RoundedCornerShape(6.dp)),
                model = filmPoster,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = filmTitle,
                    style = CardTitle,
                    color = Color.White,
                    modifier = Modifier.padding(start = 4.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row() {
                    Text(
                        text = filmCountry,
                        style = TitleMedium,
                        color = Color.White,
                        fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp)
                    )
                    Text(
                        text = "·", style = TitleMedium,
                        color = Color.White,
                        fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                    )
                    Text(
                        text = filmYear,
                        style = TitleMedium,
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                FlowRow(
                    verticalArrangement = Arrangement.Center,
                ) {
                    filmGenres.forEach { genreName ->
                        GenreTag(genreName.name)
                    }

                }


            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GenreTag(values: String = "драма") {
    Box(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp, bottom = 4.dp, top = 4.dp)
            .background(color = Gray750, shape = RoundedCornerShape(5.dp))

    ) {
        Text(
            modifier = Modifier.padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 8.dp),
            text = values, style = TitleMedium, color = Color.White, textAlign = TextAlign.Center
        )
    }
}