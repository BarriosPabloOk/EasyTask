package com.pablobarriosdevs.easytask.data.repository

import com.pablobarriosdevs.easytask.data.data_source.TaskDao
import com.pablobarriosdevs.easytask.domain.model.SubTask
import com.pablobarriosdevs.easytask.domain.model.Task
import com.pablobarriosdevs.easytask.domain.model.relations.TaskWithSubTasks
import com.pablobarriosdevs.easytask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao,
):TaskRepository {
    override suspend fun insertTask(task : Task) {
        return dao.insertTask(task)
    }

    override suspend fun insertSubTask(subTask : SubTask) {
        return dao.insertSubTask(subTask)
    }

    override fun getAllTasks(): Flow<List<TaskWithSubTasks>> {
        return dao.getAllTasks()
    }

    override suspend fun getSingleTaskWithSubTask(taskId: Int): TaskWithSubTasks? {
        return dao.getSingleTaskWithSubTask(taskId)
    }

    override suspend fun searchTask(query: String): Flow<List<TaskWithSubTasks>> {
        return dao.searchTask(query)
    }

    override suspend fun deleteTask(task : Task) {
        return dao.deleteTask(task)
    }
}