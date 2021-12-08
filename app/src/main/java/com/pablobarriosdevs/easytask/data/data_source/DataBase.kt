package com.pablobarriosdevs.easytask.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pablobarriosdevs.easytask.domain.model.SubTask
import com.pablobarriosdevs.easytask.domain.model.Task

@Database(
    entities = [Task::class, SubTask::class],
    version = 1
)

abstract class DataBase : RoomDatabase() {

    abstract val taskDao : TaskDao

    companion object{
        const val DATABASE_NAME = "easy_task_db"
    }
}