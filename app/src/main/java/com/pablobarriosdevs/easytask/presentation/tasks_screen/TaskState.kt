package com.pablobarriosdevs.easytask.presentation.tasks_screen

import com.pablobarriosdevs.easytask.domain.model.relations.TaskWithSubTasks
import com.pablobarriosdevs.easytask.domain.util.OrderType

data class TaskState(
    val tasks : List<TaskWithSubTasks> = emptyList(),
    val order : OrderType = OrderType.Ascending,
    val isSearchVisible : Boolean = false,
    val isOrderSectionVisible : Boolean = false,
)
