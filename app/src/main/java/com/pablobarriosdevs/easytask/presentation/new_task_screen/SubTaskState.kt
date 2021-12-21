package com.pablobarriosdevs.easytask.presentation.new_task_screen

data class SubTaskState(
    val id : Int? = null,
    val title : String = "",
    val isCompleted : Boolean = false,
)