package com.pablobarriosdevs.easytask.presentation.tasks_screen.components


import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.pablobarriosdevs.easytask.R

@Composable
fun ActionButton(
    onClick :()->Unit,
    background: Color,
    contentColor: Color

) {
    FloatingActionButton(
        onClick = {onClick()},
        backgroundColor = background,
        contentColor = contentColor,


    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = stringResource(id = R.string.add_task))
    }

}