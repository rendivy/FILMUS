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
import androidx.compose.ui.res.stringResource
import com.example.cinema_app.R
import com.example.cinema_app.domain.entity.DetailsDTO
import com.example.cinema_app.presentation.MovieDetailsViewModel
import com.example.cinema_app.ui.screen.details.dialog.AddReviewDialog
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SemiBoldStyle
import com.example.cinema_app.ui.theme.mediumPadding
import com.example.cinema_app.ui.theme.largePadding
import com.example.cinema_app.ui.theme.padding50

@Composable
fun ReviewHeadlines(
    content: DetailsDTO,
    movieDetailsViewModel: MovieDetailsViewModel,
    reviewDialogOpen: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = mediumPadding),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.review_label),
            modifier = Modifier
                .background(color = Gray900)
                .padding(start = mediumPadding, end = mediumPadding, bottom = mediumPadding),
            style = SemiBoldStyle
        )
        if (content.userReviewX == null) {
            Box(
                modifier = Modifier
                    .size(largePadding)
                    .background(color = Accent, shape = RoundedCornerShape(padding50))
                    .clip(RoundedCornerShape(padding50))
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