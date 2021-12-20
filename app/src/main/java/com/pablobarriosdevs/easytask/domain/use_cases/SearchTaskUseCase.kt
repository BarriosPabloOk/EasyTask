package com.pablobarriosdevs.easytask.domain.use_cases

import com.pablobarriosdevs.easytask.domain.model.relations.TaskWithSubTasks
import com.pablobarriosdevs.easytask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class SearchTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(query: String): Flow<List<TaskWithSubTasks>> = flow {
        repository.searchTask(query = query).map { tasks ->
            tasks.filter { !it.task.isCompleted }.sortedBy { it.task.created } +
                    tasks.filter { it.task.isCompleted }.sortedBy{ it.task.created }
        }
    }
}