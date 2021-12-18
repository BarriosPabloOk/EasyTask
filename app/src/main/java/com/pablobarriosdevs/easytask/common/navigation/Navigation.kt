package com.pablobarriosdevs.easytask.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pablobarriosdevs.easytask.presentation.new_task_screen.NewTaskScreen
import com.pablobarriosdevs.easytask.presentation.tasks_screen.TaskScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.TaskScreen.route){
        composable(
            route = Screen.TaskScreen.route,
        ){
            TaskScreen(navController = navController)
        }
        composable(
            route = Screen.NewTaskScreen.route,
        ){
            NewTaskScreen(navController = navController)
        }

    }

}