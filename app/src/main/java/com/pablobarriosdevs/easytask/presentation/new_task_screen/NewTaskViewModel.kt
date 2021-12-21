package com.pablobarriosdevs.easytask.presentation.new_task_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablobarriosdevs.easytask.domain.model.InvalidTaskException
import com.pablobarriosdevs.easytask.domain.model.SubTask
import com.pablobarriosdevs.easytask.domain.model.Task
import com.pablobarriosdevs.easytask.domain.use_cases.wrapper.UseCasesWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(
    private val useCasesWrapper: UseCasesWrapper,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _newTaskState = mutableStateOf<NewTaskState>(NewTaskState())
    val newTaskState: State<NewTaskState> = _newTaskState

    private val _textFieldState = mutableStateOf<TextFieldState>(TextFieldState())
    val textFieldState: State<TextFieldState> = _textFieldState

    private val _subTasks = mutableStateOf<MutableList<SubTaskState>>(mutableListOf())
    val subTasks: State<MutableList<SubTaskState>> = _subTasks

    private val _eventFlow = MutableSharedFlow<UIEvents>()
    val eventFlow: SharedFlow<UIEvents> = _eventFlow

    var currentNoteId: Int? = null

    private val idFrom = savedStateHandle.get<Int>("task_id")

    init {

        idFrom?.let { id ->
            if (id != -1) {
                viewModelScope.launch {
                    useCasesWrapper.getSingleTaskWithSubTaskUseCase(id)?.also { task ->
                        currentNoteId = task.task.idTask
                        _newTaskState.value = newTaskState.value.copy(
                            title = task.task.title,
                            desc = task.task.description,
                            currentDate = task.task.currentDate,
                            created = task.task.created
                        )
                        task.subTasks.onEach {
                            _subTasks.value.add(
                                SubTaskState(
                                    id = it.idSubTask,
                                    title = it.title,
                                    isCompleted = it.isCompleted
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: NewTaskEvent) {
        when (event) {
            is NewTaskEvent.AddSubTask -> {
                viewModelScope.launch {
                    if (event.subTaskState.title.isNotBlank()) {
                        _subTasks.value.add(event.subTaskState)
                    } else {
                        _eventFlow.emit(UIEvents.ShowSnackBar(message = "La subtarea no tiene contenido"))
                    }
                }

            }
            is NewTaskEvent.EnteredDesc -> {
                _newTaskState.value = newTaskState.value.copy(
                    desc = event.desc
                )
            }
            is NewTaskEvent.EnteredSubTaskTitle -> {
                _newTaskState.value = newTaskState.value.copy(
                    title = event.title
                )
            }
            is NewTaskEvent.CheckSubTask -> {
                _subTasks.value[event.index] = subTasks.value[event.index].copy(
                    isCompleted = !subTasks.value[event.index].isCompleted
                )

            }
            is NewTaskEvent.EnteredTitle -> {
                _newTaskState.value = newTaskState.value.copy(
                    title = event.title
                )
            }
            NewTaskEvent.SaveTask -> {
                viewModelScope.launch {
                    try {
                        useCasesWrapper.insertTaskUseCase(
                            Task(
                                idTask = currentNoteId,
                                title = _newTaskState.value.title,
                                description = _newTaskState.value.desc,
                                created = if (idFrom != -1) _newTaskState.value.created else
                                    System.currentTimeMillis(),
                                currentDate = if (idFrom != -1) _newTaskState.value.currentDate else
                                    System.currentTimeMillis(),
                                isCompleted = false
                            )
                        )
                        _subTasks.value.forEach {
                            useCasesWrapper.insertSubTaskUseCase(
                                SubTask(
                                    idSubTask = it.id,
                                    title = it.title,
                                    isCompleted = it.isCompleted,
                                    idOwnerTask = currentNoteId
                                )
                            )
                        }
                        _eventFlow.emit(UIEvents.SaveTask)
                    } catch (e: InvalidTaskException) {
                        _eventFlow.emit(
                            UIEvents.ShowSnackBar(
                                message = e.message ?: "No se pudo guardar la nota"
                            )
                        )
                    }
                }
            }

        }
    }
}
