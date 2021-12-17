package com.pablobarriosdevs.easytask.presentation.tasks_screen.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pablobarriosdevs.easytask.R
import com.pablobarriosdevs.easytask.domain.util.Priority
import java.util.*

@Composable
fun TaskComponent(
    title: String,
    date: String,
    priority:String,
    isChecked: Boolean,
    onCheckedChange: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),

        ) {
        Image(
            modifier = Modifier.align(Alignment.CenterStart),
            painter = when (priority) {
                Priority.HIGH.name -> painterResource(id = R.mipmap.priority_high)
                Priority.MEDIUM.name -> painterResource(id = R.mipmap.priority_medium)
                Priority.LOW.name -> painterResource(id = R.mipmap.priority_low)
                else -> painterResource(id = R.mipmap.alerta_sin_fondo)
            },
            contentDescription = stringResource(id = R.string.priority)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = MaterialTheme.typography.h5.fontSize,
            )
            Text(
                text = date,
                fontWeight = FontWeight.Thin,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = MaterialTheme.typography.h5.fontSize,
                color = Color.LightGray
            )

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
            )
        )
    }
}