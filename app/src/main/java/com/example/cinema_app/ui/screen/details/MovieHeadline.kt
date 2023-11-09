package com.example.cinema_app.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cinema_app.R
import com.example.cinema_app.domain.entity.DetailsDTO
import com.example.cinema_app.ui.screen.home.RatingBox
import com.example.cinema_app.ui.theme.Gray750
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.InternBoldLarge
import com.example.cinema_app.ui.theme.padding16


@Composable
fun MovieHeadline(
    content: DetailsDTO
) {
    Row(
        modifier = Modifier
            .background(color = Gray900)
            .padding(
                start = padding16,
                end = padding16,
                top = padding16,
                bottom = 20.dp
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RatingBox(content.averageFilmRating.toString())
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = content.name,
            modifier = Modifier
                .weight(1f)
                .background(color = Gray900),
            style = InternBoldLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {},
            modifier = Modifier
                .background(
                    color = Gray750,
                    shape = RoundedCornerShape(50.dp)
                )
                .width(40.dp)
                .height(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.favourite_icon),
                contentDescription = null
            )
        }

    }
}
