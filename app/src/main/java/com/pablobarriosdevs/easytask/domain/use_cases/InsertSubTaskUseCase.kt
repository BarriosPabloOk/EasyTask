package com.pablobarriosdevs.easytask.domain.use_cases

import com.pablobarriosdevs.easytask.domain.model.SubTask
import com.pablobarriosdevs.easytask.domain.repository.TaskRepository
import javax.inject.Inject


class InsertSubTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(subTask: SubTask){
        return repository.insertSubTask(subTask)
    }
}