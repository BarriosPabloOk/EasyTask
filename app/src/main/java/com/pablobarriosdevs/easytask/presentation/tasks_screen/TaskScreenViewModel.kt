package com.pablobarriosdevs.easytask.presentation.tasks_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.pablobarriosdevs.easytask.domain.use_cases.wrapper.UseCasesWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val useCasesWrapper: UseCasesWrapper
): ViewModel() {
    
    
    private val _taskState = mutableStateOf<TaskState>(TaskState())
    val taskState : State<TaskState> = _taskState

}