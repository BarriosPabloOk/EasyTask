package com.pablobarriosdevs.easytask.presentation.tasks_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun TaskComponent(
    modifier: Modifier = Modifier,
    title: String,
    subTasksAmount: Int,
    isChecked: Boolean,
    onCheckedChange: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),

        ) {

        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = MaterialTheme.typography.h6.fontSize,
            )
            if (subTasksAmount > 0) {
                Text(
                    text = "Contiene $subTasksAmount subtareas pendientes",
                    fontWeight = FontWeight.Thin,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    color = Color.LightGray
                )
            }

        }

        Checkbox(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .shadow(elevation = 4.dp),
            checked = isChecked,
            onCheckedChange = { onCheckedChange() },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.surface,
                checkmarkColor = MaterialTheme.colors.primary
            ),
        )
    }
}