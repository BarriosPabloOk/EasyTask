package com.pablobarriosdevs.easytask.presentation.tasks_screen

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablobarriosdevs.easytask.domain.model.relations.TaskWithSubTasks
import com.pablobarriosdevs.easytask.domain.use_cases.wrapper.UseCasesWrapper
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
) : ViewModel() {

    val dateStr = SimpleDateFormat("EEEE dd/MM/yyyy", Locale.getDefault())
    val nameDayStr = SimpleDateFormat("EEE ", Locale.getDefault())
    val numberDayStr = SimpleDateFormat("dd", Locale.getDefault())

    private val _taskState = mutableStateOf<TaskState>(TaskState())
    val taskState: State<TaskState> = _taskState

    private val _todayState = mutableStateOf<Date>(Date())
    val todayState: State<Date> = _todayState

    private val _monthCalendarState = mutableStateOf<List<Date>>(listOf())
    val monthCalendarState: State<List<Date>> = _monthCalendarState

    private val _lazyState = mutableStateOf<LazyListState>(LazyListState())
    val lazyState: State<LazyListState> = _lazyState

    var getTaskJob: Job? = null

    var recentlyDeleteTask: TaskWithSubTasks? = null

    init {
        viewModelScope.launch {
            _monthCalendarState.value = useCasesWrapper.rowCalendarUseCase()
            getAllTaskByDate(todayState.value.time)
        }
    }

    fun onEvent(events: TaskScreenEvents) {

        when (events) {
            is TaskScreenEvents.DeleteTask -> {
                viewModelScope.launch {

                    _taskState.value.tasks.onEach {
                        useCasesWrapper.deleteTaskUseCase(it.task)
//                        for (i in it.subTasks){useCasesWrapper.deleteSubTaskUseCase(i)}
                        recentlyDeleteTask = it

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
            is TaskScreenEvents.RestoreTask -> {
                viewModelScope.launch {
                    recentlyDeleteTask?.let {
                        useCasesWrapper.insertTaskUseCase(it.task)
                        for (i in it.subTasks) {
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
            is TaskScreenEvents.GetTasksByDate -> {
                getAllTaskByDate(
                    targetDate = taskState.value.calendar.timeInMillis,
                )
            }
            is TaskScreenEvents.CheckedDate -> {
                _taskState.value = taskState.value.copy(
                    selectedDate = events.date
                )
            }
            TaskScreenEvents.DatePickerOpen -> TODO()
        }
    }

    private fun getAllTaskByDate(targetDate: Long) {
        getTaskJob?.cancel()
        getTaskJob = useCasesWrapper
            .getTasksByCurrentDateUseCase(targetDate)
            .onEach { tasks ->
                _taskState.value = taskState.value.copy(
                    tasks = tasks,
                )
            }.launchIn(viewModelScope)
    }



}