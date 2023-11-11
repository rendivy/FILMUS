package com.example.cinema_app.ui.screen.details.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cinema_app.data.converter.BudgetConverter
import com.example.cinema_app.domain.entity.DetailsDTO
import com.example.cinema_app.ui.theme.GenreTitle
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.SemiBoldStyle
import com.example.cinema_app.ui.theme.padding16

@Composable
fun FilmDetailsSection(content: DetailsDTO) {
    SectionTitle("О фильме")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = padding16, vertical = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        AboutItem("Год", content.year.toString())
        Spacer(modifier = Modifier.height(10.dp))
        AboutItem("Cтрана", content.country)
        Spacer(modifier = Modifier.height(10.dp))
        AboutItem("Cлоган", content.tagline)
        Spacer(modifier = Modifier.height(10.dp))
        AboutItem("Режиссёр", content.director)
        Spacer(modifier = Modifier.height(10.dp))
        content.budget?.let {
            AboutItem("Бюджет", BudgetConverter.convertBudget(it))
            Spacer(modifier = Modifier.height(10.dp))
        }
        content.fees?.let {
            AboutItem("Cборы в мире", BudgetConverter.convertBudget(it))
            Spacer(modifier = Modifier.height(10.dp))}
        AboutItem("Возраст", "${content.ageLimit}+")
        Spacer(modifier = Modifier.height(10.dp))
        AboutItem("Время", "${content.time} мин")
    }
}


@Composable
fun AboutItem(title: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        ItemTitle(title)
        Spacer(modifier = Modifier.width(8.dp))
        ItemValue(value)
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .background(color = Gray900)
            .padding(horizontal = padding16, vertical = 10.dp),
        style = SemiBoldStyle
    )
}

@Composable
fun ItemTitle(title: String) {
    Text(
        text = title,
        style = GenreTitle,
        modifier = Modifier.width(100.dp)
    )
}

@Composable
fun ItemValue(value: String) {
    Text(
        text = value,
        style = GenreTitle,
        color = Color.White,
        modifier = Modifier.fillMaxWidth(),
    )
}