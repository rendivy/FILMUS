package com.example.cinema_app.ui.screen.errorUiScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cinema_app.FilmusString
import com.example.cinema_app.R
import com.example.cinema_app.ui.screen.favorite.testTags.ErrorTestTag
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SecondarySemiBoldStyle
import com.example.cinema_app.ui.theme.SemiBoldStyle
import com.example.cinema_app.ui.theme.largePadding
import com.example.cinema_app.ui.theme.maxFavouriteCardSize
import com.example.cinema_app.ui.theme.mediumPadding
import com.example.cinema_app.ui.theme.padding10
import com.example.cinema_app.ui.theme.padding15
import com.example.cinema_app.ui.theme.semiMediumPadding

@Composable
fun ErrorUiScreen(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900)
            .testTag(ErrorTestTag.ErrorScreenTestTag),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.sync),
            contentDescription = stringResource(FilmusString.wifi_error),
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.size(maxFavouriteCardSize)
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        Text(
            text = stringResource(id = R.string.error_happend),
            style = SemiBoldStyle
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = largePadding, end = largePadding)
                .testTag(ErrorTestTag.ErrorButtonTestTag),
            onClick = onClick,
            shape = RoundedCornerShape(size = padding10),
            colors = ButtonDefaults.buttonColors(
                containerColor = Accent
            ),
            contentPadding = PaddingValues(semiMediumPadding)
        ) {
            Text(
                text = stringResource(id = R.string.repeat),
                style = SecondarySemiBoldStyle
            )
        }
        Spacer(modifier = Modifier.height(padding15))
    }
}

@Composable
@Preview(showBackground = true)
private fun ErrorUiScreenPreview() {
    ErrorUiScreen(onClick = {})
}