package com.pablobarriosdevs.easytask.domain.use_cases

import com.pablobarriosdevs.easytask.domain.model.Task
import com.pablobarriosdevs.easytask.domain.repository.TaskRepository
import com.pablobarriosdevs.easytask.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class SearchTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(query: String): Flow<List<Task>> = flow {
        repository.searchTask(query = query).map { tasks ->
            tasks.filter { !it.task.isCompleted }.sortedBy { it.task.priority } +
                    tasks.filter { it.task.isCompleted }.sortedBy{ it.task.priority }
        }
    }
}