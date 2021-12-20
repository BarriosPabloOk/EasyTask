package com.pablobarriosdevs.easytask.domain.repository

import com.pablobarriosdevs.easytask.domain.model.SubTask
import com.pablobarriosdevs.easytask.domain.model.Task
import com.pablobarriosdevs.easytask.domain.model.relations.TaskWithSubTasks
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun insertTask(task : Task)//

    suspend fun insertSubTask(subTask : SubTask)//

    fun getTasksByCurrentDate(currentDate :Long): Flow<List<TaskWithSubTasks>>//

    suspend fun getSingleTaskWithSubTask(taskId: Int): TaskWithSubTasks?//

    suspend fun deleteTask(task: Task)//

    fun searchTask(query:String): Flow<List<TaskWithSubTasks>>//

//    suspend fun deleteSubTask(subTask: SubTask)

}