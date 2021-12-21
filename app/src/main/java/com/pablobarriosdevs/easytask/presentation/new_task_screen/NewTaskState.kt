package com.pablobarriosdevs.easytask.presentation.new_task_screen


data class NewTaskState(
    val title : String = "",
    val desc :String = "",
    val currentDate : Long = 0L,
    val created : Long = 0L,
)