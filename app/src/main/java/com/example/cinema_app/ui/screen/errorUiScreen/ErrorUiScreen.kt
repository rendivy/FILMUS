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
import androidx.compose.ui.res.painterResource
import com.example.cinema_app.R
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SecondarySemiBoldStyle
import com.example.cinema_app.ui.theme.SemiBoldStyle
import com.example.cinema_app.ui.theme.maxFavouriteCardSize
import com.example.cinema_app.ui.theme.padding10
import com.example.cinema_app.ui.theme.semiMediumPadding
import com.example.cinema_app.ui.theme.padding15
import com.example.cinema_app.ui.theme.mediumPadding
import com.example.cinema_app.ui.theme.largePadding

@Composable
fun ErrorUiScreen(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.sync),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.size(maxFavouriteCardSize)
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        Text(text = "Произошла ошибка", style = SemiBoldStyle)
        Spacer(modifier = Modifier.height(mediumPadding))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = largePadding, end = largePadding),
            onClick = onClick,
            shape = RoundedCornerShape(size = padding10),
            colors = ButtonDefaults.buttonColors(
                containerColor = Accent
            ),
            contentPadding = PaddingValues(semiMediumPadding)
        ) {
            Text(
                text = "Повторить",
                style = SecondarySemiBoldStyle
            )
        }
        Spacer(modifier = Modifier.height(padding15))
    }
}