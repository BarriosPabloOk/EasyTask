package com.pablobarriosdevs.easytask.presentation.tasks_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pablobarriosdevs.easytask.R
import com.pablobarriosdevs.easytask.common.format
import com.pablobarriosdevs.easytask.presentation.tasks_screen.components.*
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun TaskScreen(
    navController: NavController,
    viewModel: TaskScreenViewModel = hiltViewModel(),
) {

    val scaffoldState = rememberScaffoldState()
    val taskState = viewModel.taskState
    val monthState = viewModel.monthCalendarState
    val today = viewModel.todayState
    val lazyState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,

        floatingActionButton = {
            ActionButton(
                onClick = { /*TODO*/ },
                background = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,

        ) {
        Column(modifier = Modifier
            .fillMaxSize()

        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start) {

                Text(
                    text = stringResource(id = R.string.title),
                    fontSize = MaterialTheme.typography.h4.fontSize,
                    //fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = taskState.value.checkedDate
                        .format("EEEE dd 'de' MMMM YYYY")
                        .replaceFirstChar { it.uppercase() },
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    //fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Light
                )
                Spacer(modifier = Modifier.height(30.dp))

                Divider()
                LazyRow(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 7.dp),
                    state = lazyState
                ){
                    items( monthState.value){day ->
                        DayComponent(
                            dayName =viewModel.nameDayStr.format(day)  ,
                            dayNumber =viewModel.numberDayStr.format(day)  ,
                            colorBackground = if (today.value.format() == day.format() )
                                MaterialTheme.colors.primary else Color.White,
                            colorText = if (today.value.format() == day.format()  )
                                MaterialTheme.colors.onPrimary else Color.Black.copy(alpha = 0.7f),
                            isChecked = taskState.value.checkedDate == day,
                            clickable = {viewModel.onEvent(TaskScreenEvents.CheckedDate(day))}
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        coroutineScope.launch {
                            monthState.value.onEachIndexed(){index, date ->
                                if (today.value.format() ==
                                    date.format() )
                                        lazyState.animateScrollToItem(index = index, scrollOffset = 0)
                            }

                        }
                    }
                    item { MoreDatesComponent(clickable = {}) }

                }
                Divider()
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
                items(taskState.value.tasks) { task ->
                    TaskComponent(
                        title = task.task.title,
                        date = viewModel.dateStr.format(task.task.todayDate),
                        priority = task.task.priorityString,
                        isChecked = task.task.isCompleted
                    ) {
                    }
                }
            }
        }


    }

}