package com.pablobarriosdevs.easytask.presentation.tasks_screen

import com.pablobarriosdevs.easytask.domain.model.relations.TaskWithSubTasks
import com.pablobarriosdevs.easytask.domain.util.OrderType
import java.util.*

sealed class TaskScreenEvents{
    data class TaskByOrder(val order : OrderType): TaskScreenEvents()
    data class EnterSearch(val query:String): TaskScreenEvents()
    data class DeleteTask(val taskWithSubTasks: TaskWithSubTasks): TaskScreenEvents()
    data class CheckedDate(val date : Date): TaskScreenEvents()
    object RestoreTask: TaskScreenEvents()
    object SearchFieldVisibility : TaskScreenEvents()
    object OrderSectionVisibility : TaskScreenEvents()
}
