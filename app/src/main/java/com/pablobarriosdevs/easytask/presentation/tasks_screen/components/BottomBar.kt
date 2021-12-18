package com.pablobarriosdevs.easytask.presentation.tasks_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pablobarriosdevs.easytask.R
import com.pablobarriosdevs.easytask.domain.util.OrderType

@Composable
fun BottomBar(
    background: Color,
    contentColor : Color,
    order: () -> Unit,
    orderType: OrderType,
    highFirst: () -> Unit,

    ) {

    BottomAppBar(
        backgroundColor = background,
        contentColor = contentColor,
        cutoutShape = CircleShape
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = { order() },
                modifier = Modifier.padding(end = 70.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(0.dp),
                    imageVector = if (orderType is OrderType.Descending)
                        Icons.Rounded.ArrowDownward else Icons.Rounded.ArrowUpward,
                    contentDescription = stringResource(id = R.string.order_type)
                )

            }

            IconButton(
                onClick = { highFirst() }
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = stringResource(id = R.string.order_type)
                )

            }
        }
    }

}