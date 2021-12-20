package com.pablobarriosdevs.easytask.domain.use_cases

import com.pablobarriosdevs.easytask.domain.model.relations.TaskWithSubTasks
import com.pablobarriosdevs.easytask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetTasksByCurrentDateUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    operator fun invoke(currentDate: Long): Flow<List<TaskWithSubTasks>> = flow {
        repository.getTasksByCurrentDate(currentDate).map {
                tasks -> tasks.sortedBy { it.task.created }
        }
    }
}
