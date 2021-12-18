package com.pablobarriosdevs.easytask.presentation.tasks_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.pablobarriosdevs.easytask.common.makeFormat

@Composable
fun DayComponent(
    clickable : ()->Unit,
    size: Dp = 70.dp,
    cornerRadius: Dp = 20.dp,
    colorBackground: Color = MaterialTheme.colors.primary,
    colorText: Color = MaterialTheme.colors.onPrimary,
    colorBorder: Color = MaterialTheme.colors.primary,
    dayName: String,
    dayNumber: String,
    isChecked: Boolean,


) {

    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(cornerRadius))
            .background(colorBackground)
            .border(
                width = 2.dp,
                color = if (isChecked) colorBorder else Color.Transparent,
                shape = RoundedCornerShape(cornerRadius)
            ).clickable { clickable() }

    ) {
        Column(
            modifier = Modifier.size(size),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = dayName.makeFormat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.h6.fontSize,
                color = colorText,
            )
            Text(
                text = dayNumber,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.h5.fontSize,
                color = colorText,
            )

        }
    }

}

@Preview
@Composable
fun Preview() {
    DayComponent(
        dayName = "lun",
        dayNumber = "10",
        isChecked = false,
        clickable = {}
        )
}