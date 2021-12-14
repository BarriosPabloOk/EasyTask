package com.pablobarriosdevs.easytask.presentation.tasks_screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pablobarriosdevs.easytask.presentation.tasks_screen.components.ActionButton
import com.pablobarriosdevs.easytask.presentation.tasks_screen.components.BottomBar

@Composable
fun TaskScreen(
    navController: NavController,
    viewModel: TaskScreenViewModel = hiltViewModel(),
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {},
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
        isFloatingActionButtonDocked = true
    ) {

    }

}