package com.pablobarriosdevs.easytask.presentation.tasks_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pablobarriosdevs.easytask.presentation.tasks_screen.TaskScreenViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun WeeklyRowCalendar(
    daysList: List<Date>,
    todayDate: Date,
    dayFormat: (Date) -> String,
    numberFormat: (Date) -> String,
    checkedDate: Date,

    ) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    )
    val lazyState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        state = lazyState
    ) {

        items(daysList) { day ->

            DayComponent(
                dayName = dayFormat(day),
                dayNumber = numberFormat(day),
                colorBackground = if (todayDate.time == day.time)
                    MaterialTheme.colors.primary else Color.White,
                colorText = if (todayDate.time == day.time)
                    MaterialTheme.colors.onPrimary else Color.Black.copy(alpha = 0.7f),
                isChecked = day == checkedDate
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
        item { MoreDatesComponent() }


    }

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    )
    LaunchedEffect(key1 = scope,){
        daysList.onEachIndexed { index, date ->
            if (date.time == todayDate.time) lazyState.animateScrollToItem(
                index = index,
                scrollOffset = 5)
        }
    }

}