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
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val useCasesWrapper: UseCasesWrapper
): ViewModel() {

    val dateStr = SimpleDateFormat("EEEE dd/MM/yyyy", Locale.getDefault())
    val nameDayStr = SimpleDateFormat("EEE ", Locale.getDefault())
    val numberDayStr = SimpleDateFormat("dd", Locale.getDefault())

    private val _taskState = mutableStateOf<TaskState>(TaskState())
    val taskState : State<TaskState> = _taskState

    private val _todayState = mutableStateOf<Date>(Date())
    val todayState : State<Date> = _todayState

    private val _weekState = mutableStateOf<List<Date>>(listOf())
    val weekState : State<List<Date>> = _weekState

    private val _calendarState = mutableStateOf<Calendar>(Calendar.getInstance())
    val calendarState : State<Calendar> = _calendarState

    var getTaskJob : Job? = null

    var recentlyDeleteTask : TaskWithSubTasks? = null

    init {

        viewModelScope.launch {
            _weekState.value = useCasesWrapper.rowCalendarUseCase(calendarState.value)
            useCasesWrapper.getAllTasksByTargetDateUseCase(
                todayState.value.time,
                OrderType.Descending)

        }

    }

    fun onEvent(events: TaskScreenEvents){

        when(events){
            is TaskScreenEvents.DeleteTask -> {
                viewModelScope.launch {

                    _taskState.value.tasks.onEach {
                        useCasesWrapper.deleteTaskUseCase(it.task)
//                        for (i in it.subTasks){useCasesWrapper.deleteSubTaskUseCase(i)}
                        recentlyDeleteTask =it

                    }

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