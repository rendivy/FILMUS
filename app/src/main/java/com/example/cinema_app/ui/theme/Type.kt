package com.example.cinema_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val TitleMedium = TextStyle(
    fontFamily = InterMedium,
    fontWeight = FontWeight(500),
    fontSize = 15.sp,
    color = Color.White
)

val TitleSmall = TextStyle(
    fontFamily = InterRegular,
    fontWeight = FontWeight(400),
    fontSize = 15.sp,
    color = Color.White
)

val SecondaryAccentStyle = TextStyle(
    fontFamily = InternSemiBold,
    fontWeight = FontWeight(600),
    fontSize = 15.sp,
    color = Color.White
)

val SemiBoldStyle = TextStyle(
    fontFamily = InternSemiBold,
    fontWeight = FontWeight(700),
    fontSize = 20.sp,
    color = Color.White
)

val SecondarySemiBoldStyle = TextStyle(
    fontFamily = InterRegular,
    fontWeight = FontWeight(600),
    fontSize = 15.sp,
    color = Color.White
)

val TitleLarge = TextStyle(
    fontFamily = InterBold,
    fontWeight = FontWeight(800),
    fontSize = 20.sp,
    color = Color.White
)

/* Other default text styles to override
titleLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
),

*/
