package com.pablobarriosdevs.easytask.presentation.tasks_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pablobarriosdevs.easytask.R

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
        IconButton(onClick = { onCheckedChange() }) {
            Icon(modifier = Modifier.size(10.dp),
                painter = if (isChecked) painterResource(id = R.mipmap.checked) else
                    painterResource(id = R.mipmap.unchecked),
                contentDescription = stringResource(id = R.string.check_button_icon)
            )
        }
    }
}