package com.example.cinema_app.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinema_app.R
import com.example.cinema_app.ui.theme.BadRatingColor
import com.example.cinema_app.ui.theme.GoodRatingColor
import com.example.cinema_app.ui.theme.MediumRatingColor
import com.example.cinema_app.ui.theme.SemiBadRatingColor
import com.example.cinema_app.ui.theme.SemiMediumRatingColor
import com.example.cinema_app.ui.theme.tinyPadding
import com.example.cinema_app.ui.theme.TitleMedium
import com.example.cinema_app.ui.theme.mediumPadding

@Composable
fun UserRatingBox(userRating: Int) {
    val color = when (userRating) {
        in 0..2 -> BadRatingColor
        in 2..4 -> SemiBadRatingColor
        in 4..6 -> SemiMediumRatingColor
        in 6..8 -> MediumRatingColor
        in 8..10 -> GoodRatingColor
        else -> Color.White
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(35.dp))
            .background(color),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.star_3),
                contentDescription = "star rating",
                modifier = Modifier
                    .padding(
                        start = tinyPadding,
                        end = tinyPadding,
                        top = tinyPadding,
                        bottom = tinyPadding
                    )
                    .width(mediumPadding)
                    .height(mediumPadding),
            )
            Text(
                modifier = Modifier.padding(
                    end = tinyPadding,
                    top = tinyPadding,
                    bottom = tinyPadding
                ),
                text = "$userRating",
                style = TitleMedium,
                color = Color.White,
                fontSize = 16.sp,
            )
        }

    }
}
