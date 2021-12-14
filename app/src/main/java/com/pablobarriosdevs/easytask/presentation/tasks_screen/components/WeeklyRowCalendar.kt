package com.pablobarriosdevs.easytask.presentation.tasks_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pablobarriosdevs.easytask.presentation.tasks_screen.TaskScreenViewModel
import java.util.*

@Composable
fun WeeklyRowCalendar(
    daysList: List<Date>,
    todayDate: Date,
    dayFormat :() ->String,
    numberFormat:()-> String
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ){
        items(daysList){day->

            DayComponent(
                dayName = dayFormat(),
                dayNumber = numberFormat(),
                colorBackground = if(todayDate.time == day.time)
                    MaterialTheme.colors.primary else Color.White,
                colorText = if(todayDate.time == day.time)
                    MaterialTheme.colors.onPrimary else Color.Black.copy(alpha = 0.7f),
            )
        }
        item { MoreDatesComponent() }

    }

}