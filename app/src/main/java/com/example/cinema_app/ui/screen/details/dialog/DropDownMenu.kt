package com.example.cinema_app.ui.screen.details.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cinema_app.R
import com.example.cinema_app.ui.screen.details.dropdown.DropdownMenuNoPaddingVertical
import com.example.cinema_app.ui.theme.Gray750
import com.example.cinema_app.ui.theme.TitleMedium

@Composable
fun CustomDropDownMenu(onDelete: () -> Unit, onEdit: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.CenterEnd),
        contentAlignment = Alignment.CenterStart
    ) {
        IconButton(
            onClick = {
                expanded = true
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.drop_down_button_icon),
                contentDescription = "Open Menu"
            )
        }
        DropdownMenuNoPaddingVertical(
            expanded = expanded,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(177.dp)
                .background(color = Gray750, shape = RoundedCornerShape(size = 10.dp)),

            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onEdit() },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Редактировать", color = Color.White,
                            textAlign = TextAlign.Start, style = TitleMedium
                        )
                        Icon(
                            painterResource(id = R.drawable.edit_review_icon),
                            tint = Color.White,
                            contentDescription = null
                        )
                    }

                },
                onClick = {
                    expanded = false
                },
            )
            HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            DropdownMenuItem(
                text = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = onDelete),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Удалить", color = Color.Red, textAlign = TextAlign.Start,
                            style = TitleMedium
                        )
                        Icon(
                            painterResource(id = R.drawable.delete_review_button),
                            tint = Color.Red,
                            contentDescription = null
                        )
                    }

                },
                onClick = {
                    expanded = false
                },
            )
        }
    }
}

