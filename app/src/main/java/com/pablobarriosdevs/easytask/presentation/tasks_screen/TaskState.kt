package com.pablobarriosdevs.easytask.presentation.tasks_screen

import com.pablobarriosdevs.easytask.domain.model.relations.TaskWithSubTasks
import com.pablobarriosdevs.easytask.domain.util.OrderType
import java.util.*

data class TaskState(
    val tasks : List<TaskWithSubTasks> = emptyList(),
    val order : OrderType = OrderType.Ascending,
    val date : Calendar = Calendar.getInstance(),
    val isSearchVisible : Boolean = false,
    val isOrderSectionVisible : Boolean = false,
)
