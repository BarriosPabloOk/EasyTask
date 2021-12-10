package com.pablobarriosdevs.easytask.presentation.tasks_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablobarriosdevs.easytask.domain.use_cases.wrapper.UseCasesWrapper
import com.pablobarriosdevs.easytask.domain.util.OrderType
import com.pablobarriosdevs.easytask.presentation.tasks_screen.components.TaskScreenEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val useCasesWrapper: UseCasesWrapper
): ViewModel() {
    
    
    private val _taskState = mutableStateOf<TaskState>(TaskState())
    val taskState : State<TaskState> = _taskState

    var getTaskJob : Job? = null

    init {

    }

    fun onEvent(events: TaskScreenEvents){
        when(events){
            is TaskScreenEvents.DeleteTask -> TODO()
            is TaskScreenEvents.EnterSearch -> TODO()
            is TaskScreenEvents.OrderSectionVisibility -> TODO()
            is TaskScreenEvents.RestoreTask -> TODO()
            is TaskScreenEvents.SearchFieldVisibility -> TODO()
            is TaskScreenEvents.TaskByOrder -> TODO()
        }
    }

    fun getAllTaskByDate(targetDate : Long, orderType: OrderType){
        getTaskJob?.cancel()
        getTaskJob = useCasesWrapper
            .getAllTasksByTargetDateUseCase(targetDate, orderType = orderType)
            .onEach { tasks->
                _taskState.value = taskState.value.copy(
                    tasks = tasks,
                    order = orderType
                )
            }.launchIn(viewModelScope)
    }

}