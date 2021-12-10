package com.pablobarriosdevs.easytask.presentation.tasks_screen.components

import com.pablobarriosdevs.easytask.domain.model.Task
import com.pablobarriosdevs.easytask.domain.model.relations.TaskWithSubTasks
import com.pablobarriosdevs.easytask.domain.util.OrderType

sealed class TaskScreenEvents{
    data class TaskByOrder(val order : OrderType): TaskScreenEvents()
    data class EnterSearch(val query:String, val order: OrderType): TaskScreenEvents()
    data class DeleteTask(val task: Task): TaskScreenEvents()
    object RestoreTask: TaskScreenEvents()
    object SearchFieldVisibility : TaskScreenEvents()
    object OrderSectionVisibility : TaskScreenEvents()
}
