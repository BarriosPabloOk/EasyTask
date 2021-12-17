package com.pablobarriosdevs.easytask.presentation.tasks_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pablobarriosdevs.easytask.R
import com.pablobarriosdevs.easytask.presentation.tasks_screen.components.*

@Composable
fun TaskScreen(
    //navController: NavController,
    viewModel: TaskScreenViewModel = hiltViewModel(),
) {

    val scaffoldState = rememberScaffoldState()
    val taskState = viewModel.taskState

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
                 TopBar(
                     background = Color.White ,
                     contentColor = Color.LightGray,
                     searchAction = {},
                     title = stringResource(id = R.string.title),
                     todayDate = viewModel.dateStr.format(viewModel.todayState.value.time)
                 )
            WeeklyRowCalendar(
                daysList = viewModel.monthCalendarState.value,
                todayDate =viewModel.todayState.value ,
                dayFormat = { viewModel.nameDayStr.format(it) },
                numberFormat = { viewModel.numberDayStr.format(it)},
                checkedDate = viewModel.taskState.value.checkedDate,

                )
        },
        bottomBar = {
            BottomBar(
                background =MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                order = {},
                orderType = viewModel.taskState.value.order,
                highFirst = {})
        },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ){
            items(taskState.value.tasks){task->
                TaskComponent(
                    title = task.task.title,
                    date = viewModel.dateStr.format(task.task.todayDate),
                    priority = task.task.priorityString,
                    isChecked = task.task.isCompleted) {
                }
            }
        }
    }

}