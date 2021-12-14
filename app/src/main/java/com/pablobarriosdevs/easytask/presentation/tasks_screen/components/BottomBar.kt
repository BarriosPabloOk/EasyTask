package com.pablobarriosdevs.easytask.presentation.tasks_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropUp
import androidx.compose.runtime.Composable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
        contentColor = contentColor
    ) {

        IconButton(
            onClick = { order() }
        ) {
            Icon(
                imageVector = if (orderType is OrderType.Descending)
                    Icons.Rounded.ArrowDropUp else Icons.Rounded.ArrowDropDown,
                contentDescription = stringResource(id = R.string.order_type)
            )

        }

        IconButton(
            onClick = { highFirst() }
        ) {
            Icon(
                painter = painterResource(id = R.mipmap.alerta_sin_fondo),
                contentDescription = stringResource(id = R.string.priority)
            )

        }


    }

}