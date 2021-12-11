package com.pablobarriosdevs.easytask.presentation.tasks_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DayComponent() {

    Box(modifier = Modifier
        .clip(RoundedCornerShape(20.dp))
        .size(100.dp)
        .background(MaterialTheme.colors.secondary))
}

@Preview
@Composable
fun Preview() {
    DayComponent()
}