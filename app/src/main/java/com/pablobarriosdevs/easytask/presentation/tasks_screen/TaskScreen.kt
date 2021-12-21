package com.pablobarriosdevs.easytask.presentation.tasks_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

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
import com.pablobarriosdevs.easytask.common.formatLong
import com.pablobarriosdevs.easytask.presentation.tasks_screen.components.*

@Composable
fun TaskScreen(
    navController: NavController,
    viewModel: TaskScreenViewModel = hiltViewModel(),
) {

    val scaffoldState = rememberScaffoldState()
    val taskState = viewModel.taskState
    val monthState = viewModel.monthCalendarState
    val today = viewModel.todayState
    val lazyState = viewModel.lazyState.value
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
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start
            ) {

                DateDisplay(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    currentDate = taskState.value.selectedDate
                )
                Text(
                    text = stringResource(id = R.string.swipe_to),
                    fontSize = MaterialTheme.typography.body2.fontSize,
                    fontWeight = FontWeight.Thin
                )
                Spacer(modifier = Modifier.height(30.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                ) {
                    items(taskState.value.tasks) { task ->
                        TaskComponent(
                            title = task.task.title,
                            subTasksAmount = task.subTasks.size,
                            isChecked = task.task.isCompleted
                        ) {
                        }
                    }
                }
            }


        }


    }
}