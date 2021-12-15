package com.pablobarriosdevs.easytask.presentation.tasks_screen.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.pablobarriosdevs.easytask.R


@Composable
fun TopBar(
    background: Color,
    contentColor: Color,
    title: String,
    todayDate : String,
    searchAction : () ->Unit

) {
    TopAppBar(
        title = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.h2.fontSize,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = todayDate,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    //fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Thin
                )

            }
        },
        backgroundColor = background,
        contentColor = contentColor,
        modifier = Modifier
            .height(100.dp),
        actions = {

            IconButton(onClick = {searchAction() }) {
                Icon(imageVector = Icons.Rounded.Search,
                    contentDescription = stringResource(id = R.string.search_desc)
                )
            }
        }
        )
}