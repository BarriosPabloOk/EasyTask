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

    suspend operator fun invoke(query: String, orderType: OrderType): Flow<List<Task>> = flow {
        repository.searchTask(query = query).map { tasks ->
            when (orderType) {
                OrderType.Ascending -> { tasks.sortedBy { it.task.priority }}
                OrderType.Descending -> { tasks.sortedBy { it.task.priority }}
            }
        }
    }
}