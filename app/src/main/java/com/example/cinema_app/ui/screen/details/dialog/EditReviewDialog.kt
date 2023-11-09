package com.example.cinema_app.ui.screen.details.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.cinema_app.R
import com.example.cinema_app.presentation.MovieDetailsViewModel
import com.example.cinema_app.ui.component.CustomTextField
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Black300
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SecondaryAccentStyle
import com.example.cinema_app.ui.theme.SecondarySemiBoldStyle
import com.example.cinema_app.ui.theme.TitleLarge
import com.example.cinema_app.ui.theme.padding8
import com.gowtham.ratingbar.RatingBar


@Composable
fun EditReviewDialog(
    dialogIsOpen: MutableState<Boolean>,
    movieDetailsViewModel: MovieDetailsViewModel,
    movieId: String,
    ratingId: String
) {
    val buttonAlpha =
        if (movieDetailsViewModel.reviewState.value.reviewText.isNotEmpty()) 1f else 0.45f
    Dialog(
        onDismissRequest = { dialogIsOpen.value = false },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    )
    {
        Card(
            shape = RoundedCornerShape(padding8),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 31.dp, end = 31.dp),
            elevation = CardDefaults.cardElevation(padding8)
        ) {
            Column(
                modifier = Modifier
                    .background(Gray900)
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Gray900),
                    text = "Оставить отзыв",
                    fontSize = 24.sp,
                    style = TitleLarge,
                )
                Spacer(modifier = Modifier.height(15.dp))
                RatingBar(
                    value = movieDetailsViewModel.reviewState.value.rating,
                    painterEmpty = painterResource(id = R.drawable.empty_star),
                    painterFilled = painterResource(id = R.drawable.fill_star),
                    onValueChange = {
                        movieDetailsViewModel.setRating(it)
                    },
                    spaceBetween = 5.dp,
                    numOfStars = 10,
                    onRatingChanged = {},
                    size = 24.dp
                )
                Spacer(modifier = Modifier.height(padding8))
                CustomTextField(
                    textFieldValue = movieDetailsViewModel.reviewState.value.reviewText,
                    onValueChange = { movieDetailsViewModel.setReviewText(it) },
                    singleLine = false,
                    modifier = Modifier.height(100.dp)
                )
                Spacer(modifier = Modifier.height(padding8))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(buttonAlpha),
                    onClick = {
                        if (movieDetailsViewModel.reviewState.value.reviewText.isNotEmpty()) {
                            movieDetailsViewModel.editReview(
                                movieId = movieId,
                                reviewId = ratingId
                            )
                        }
                    },
                    enabled = movieDetailsViewModel.reviewState.value.reviewText.isNotEmpty(),
                    shape = RoundedCornerShape(size = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Accent,
                        disabledContainerColor = Accent
                    ),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    Text(
                        text = "Cохранить",
                        style = SecondarySemiBoldStyle
                    )
                }
                Spacer(modifier = Modifier.height(padding8))
                Button(
                    onClick = {
                        dialogIsOpen.value = false
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(size = 10.dp),
                    contentPadding = PaddingValues(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Black300
                    )
                ) {
                    Text(
                        text = "Отмена",
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        color = Accent,
                        style = SecondaryAccentStyle,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}