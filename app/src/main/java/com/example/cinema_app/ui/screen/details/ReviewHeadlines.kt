package com.example.cinema_app.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cinema_app.R
import com.example.cinema_app.domain.entity.DetailsDTO
import com.example.cinema_app.presentation.MovieDetailsViewModel
import com.example.cinema_app.ui.screen.details.dialog.AddReviewDialog
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SemiBoldStyle
import com.example.cinema_app.ui.theme.padding16

@Composable
fun ReviewHeadlines(
    content: DetailsDTO,
    movieDetailsViewModel: MovieDetailsViewModel,
    reviewDialogOpen: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = padding16),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Отзывы",
            modifier = Modifier
                .background(color = Gray900)
                .padding(start = padding16, end = padding16, bottom = padding16),
            style = SemiBoldStyle
        )
        if (content.userReviewX == null) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(color = Accent, shape = RoundedCornerShape(50.dp))
                    .clip(RoundedCornerShape(50.dp))
            ) {
                IconButton(onClick = {
                    reviewDialogOpen.value = true
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_film_icon),
                        contentDescription = null
                    )
                }
            }
        }
    }
    if (reviewDialogOpen.value) {
        AddReviewDialog(reviewDialogOpen, movieDetailsViewModel, movieId = content.id)
    }
}