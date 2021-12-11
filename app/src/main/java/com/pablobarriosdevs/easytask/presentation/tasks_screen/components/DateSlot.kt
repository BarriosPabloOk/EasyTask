package com.pablobarriosdevs.easytask.presentation.tasks_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pablobarriosdevs.easytask.presentation.ui.theme.Shapes
import java.util.*

@Composable
fun DateSlot(
    date : Calendar,
    isChecked : Boolean,
    cornerRadius: Dp = 20.dp
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(MaterialTheme.colors.primary)
            .size(100.dp)


    )

}

@Preview
@Composable
fun Preview() {
    DateSlot(
        date = Calendar.getInstance(),
        isChecked = false)
}