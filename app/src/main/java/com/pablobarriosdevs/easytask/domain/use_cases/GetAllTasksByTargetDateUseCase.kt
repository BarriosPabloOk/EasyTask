package com.pablobarriosdevs.easytask.domain.use_cases

import com.pablobarriosdevs.easytask.domain.model.relations.TaskWithSubTasks
import com.pablobarriosdevs.easytask.domain.repository.TaskRepository
import com.pablobarriosdevs.easytask.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetAllTasksByTargetDateUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(taskDate :Long, orderType: OrderType): Flow<List<TaskWithSubTasks>> = flow {
        repository.getAllTasksByTargetDate(taskDate).map { tasks->
            when (orderType) {
                is OrderType.Ascending -> {
                    tasks.filter { !it.task.isCompleted }.sortedBy { it.task.priority } +
                            tasks.filter { it.task.isCompleted }.sortedBy { it.task.priority }
                }
                is OrderType.Descending -> {
                    tasks.filter { !it.task.isCompleted }.sortedByDescending { it.task.priority } +
                            tasks.filter { it.task.isCompleted }.sortedByDescending { it.task.priority }
                }
            }
        }
    }
}