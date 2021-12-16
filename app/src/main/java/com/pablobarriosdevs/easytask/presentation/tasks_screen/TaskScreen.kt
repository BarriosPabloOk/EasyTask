package com.pablobarriosdevs.easytask.presentation.tasks_screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.pablobarriosdevs.easytask.R
import com.pablobarriosdevs.easytask.presentation.tasks_screen.components.ActionButton
import com.pablobarriosdevs.easytask.presentation.tasks_screen.components.BottomBar
import com.pablobarriosdevs.easytask.presentation.tasks_screen.components.TopBar
import com.pablobarriosdevs.easytask.presentation.tasks_screen.components.WeeklyRowCalendar

@Composable
fun TaskScreen(
    //navController: NavController,
    viewModel: TaskScreenViewModel = hiltViewModel(),
) {

    val scaffoldState = rememberScaffoldState()

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
        WeeklyRowCalendar(
            daysList = viewModel.monthCalendarState.value,
            todayDate =viewModel.todayState.value ,
            dayFormat = { viewModel.nameDayStr.format(it) },
            numberFormat = { viewModel.numberDayStr.format(it)},
            checkedDate = viewModel.taskState.value.checkedDate,

        )



    }

}