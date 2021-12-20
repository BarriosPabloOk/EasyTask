package com.pablobarriosdevs.easytask.presentation.tasks_screen

import com.pablobarriosdevs.easytask.domain.model.relations.TaskWithSubTasks
import java.util.*

data class TaskState(
    val tasks : List<TaskWithSubTasks> = emptyList(),
    val calendar : Calendar = Calendar.getInstance(),
    val selectedDate: Date = Date(),
    val isSearchVisible : Boolean = false,
)
