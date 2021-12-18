package com.pablobarriosdevs.easytask.presentation.tasks_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pablobarriosdevs.easytask.common.format
import java.util.*

@Composable
fun MonthlyRowCalendar(
    daysList: List<Date>,
    dayFormat: (Date) -> String,
    numberFormat: (Date) -> String,
    checkedDate: Date,
    calendar: Calendar,



    ) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            ,
    ) {

        items(daysList) { day ->

            DayComponent(
                dayName = dayFormat(day),
                dayNumber = numberFormat(day),
                colorBackground = if (calendar.get(Calendar.DAY_OF_MONTH) == day.format("dd").toInt() )
                    MaterialTheme.colors.primary else Color.White,
                colorText = if (calendar.get(Calendar.DAY_OF_MONTH) == day.format("dd").toInt())
                    MaterialTheme.colors.onPrimary else Color.Black.copy(alpha = 0.7f),
                isChecked = day.format("dd").toInt() == checkedDate.format("dd").toInt(),
                clickable = {}

            )
            Spacer(modifier = Modifier.width(5.dp))

        }
        item { MoreDatesComponent(clickable = {}) }


    }

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    )

}