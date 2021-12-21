package com.pablobarriosdevs.easytask.presentation.new_task_screen

import com.pablobarriosdevs.easytask.presentation.tasks_screen.TaskScreenEvents

sealed class NewTaskEvent{

    data class EnteredTitle(val title: String): NewTaskEvent()
    data class EnteredDesc(val desc: String): NewTaskEvent()
    data class EnteredSubTaskTitle(val title: String): NewTaskEvent()
    data class AddSubTask(val subTaskState: SubTaskState): NewTaskEvent()
    data class CheckSubTask(val index : Int): NewTaskEvent()
    object SaveTask : NewTaskEvent()
}
