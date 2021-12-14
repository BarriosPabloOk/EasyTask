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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pablobarriosdevs.easytask.R

@Composable
fun MoreDatesComponent(
    size: Dp = 100.dp,
    cornerRadius: Dp = 20.dp,
    colorBackground: Color = MaterialTheme.colors.primary,
    colorText: Color = MaterialTheme.colors.onPrimary,
) {

    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(cornerRadius))
            .background(colorBackground),
        contentAlignment = Alignment.Center
    ) {


        Text(
            text = stringResource(R.string.more_component),
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.h5.fontSize,
            color = colorText,
        )


    }

}

@Preview
@Composable
fun MoreDatesPreview() {
    MoreDatesComponent()
}
