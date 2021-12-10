package com.pablobarriosdevs.easytask.domain.use_cases

import com.pablobarriosdevs.easytask.domain.model.relations.TaskWithSubTasks
import com.pablobarriosdevs.easytask.domain.repository.TaskRepository
import javax.inject.Inject


class GetSingleTaskWithSubTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(taskId : Int): TaskWithSubTasks?{
        return repository.getSingleTaskWithSubTask(taskId)
    }
}