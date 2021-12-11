package com.pablobarriosdevs.easytask.presentation.tasks_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DayComponent(
    size: Dp = 100.dp,
    cornerRadius: Dp = 20.dp,
    colorBackground: Color = MaterialTheme.colors.primary,
    colorText : Color = MaterialTheme.colors.onPrimary,
    fontFamily :FontFamily = FontFamily.Default,
    dayName: String,
    dayNumber: Int,
) {

    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(cornerRadius))
            .background(colorBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = dayName.uppercase(),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.h6.fontSize,
                color = colorText,
                fontFamily = fontFamily
            )
            Text(
                text = dayNumber.toString(),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.h4.fontSize,
                color = colorText,
                fontFamily = fontFamily
            )

        }
    }

}

@Preview
@Composable
fun Preview() {
    DayComponent(
        dayName = "lun",
        dayNumber = 10
    )
}