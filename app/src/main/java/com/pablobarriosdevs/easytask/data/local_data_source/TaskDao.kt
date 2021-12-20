package com.pablobarriosdevs.easytask.data.local_data_source

import androidx.room.*
import com.pablobarriosdevs.easytask.domain.model.SubTask
import com.pablobarriosdevs.easytask.domain.model.Task
import com.pablobarriosdevs.easytask.domain.model.relations.TaskWithSubTasks
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task : Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubTask(subTask: SubTask)

    @Transaction
    @Query("SELECT * FROM task WHERE currentDate = :currentDate")
    fun getTasksByCurrentDate(currentDate : Long): Flow<List<TaskWithSubTasks>>

    @Transaction
    @Query("SELECT * FROM task WHERE idTask = :taskId")
    suspend fun getSingleTaskWithSubTask(taskId: Int): TaskWithSubTasks?

    @Transaction
    @Query("SELECT * FROM task WHERE title LIKE '%' || :query || '%'")
    fun searchTask(query:String): Flow<List<TaskWithSubTasks>>


    @Delete
    suspend fun deleteTask(task: Task)

//    @Delete
//    suspend fun deleteSubTask(subTask: SubTask)
}















