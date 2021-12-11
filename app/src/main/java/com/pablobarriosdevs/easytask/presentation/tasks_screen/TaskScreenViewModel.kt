package com.pablobarriosdevs.easytask.presentation.tasks_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablobarriosdevs.easytask.domain.model.relations.TaskWithSubTasks
import com.pablobarriosdevs.easytask.domain.use_cases.wrapper.UseCasesWrapper
import com.pablobarriosdevs.easytask.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val useCasesWrapper: UseCasesWrapper
): ViewModel() {
    
    
    private val _taskState = mutableStateOf<TaskState>(TaskState())
    val taskState : State<TaskState> = _taskState

    var getTaskJob : Job? = null

    var recentlyDeleteTask : TaskWithSubTasks? = null

    init {

    }

    fun onEvent(events: TaskScreenEvents){

        when(events){
            is TaskScreenEvents.DeleteTask -> {
                viewModelScope.launch {
                    useCasesWrapper.deleteTaskUseCase(events.taskWithSubTasks)
                }
                recentlyDeleteTask = events.taskWithSubTasks
            }
            is TaskScreenEvents.EnterSearch -> {
                viewModelScope.launch {
                    useCasesWrapper.searchTaskUseCase(events.query).onEach {
                            _taskState.value = taskState.value.copy(
                                tasks = it
                            )
                        }
                }
            }
            is TaskScreenEvents.OrderSectionVisibility -> {
                _taskState.value = taskState.value.copy(
                    isOrderSectionVisible = true
                )
            }
            is TaskScreenEvents.RestoreTask -> {
                viewModelScope.launch {
                     recentlyDeleteTask?.let {
                         useCasesWrapper.insertTaskUseCase(it.task)
                         for (i in it.subTasks){
                             useCasesWrapper.insertSubTaskUseCase(i)
                         }
                     }

                }
            }
            is TaskScreenEvents.SearchFieldVisibility -> {
                _taskState.value = taskState.value.copy(
                    isSearchVisible = !taskState.value.isSearchVisible
                )
            }
            is TaskScreenEvents.TaskByOrder -> {
                if(_taskState.value.order::class == events.order::class){ return}
                getAllTaskByDate(
                    targetDate = taskState.value.date.timeInMillis,
                    orderType = events.order)
            }
        }
    }

    private fun getAllTaskByDate(targetDate : Long, orderType: OrderType){
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