package com.pablobarriosdevs.easytask.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = arrayOf("idTask"),
            childColumns = arrayOf("idOwner"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SubTask(
    @PrimaryKey(autoGenerate = true) val idSubTask : Int,
    val title: String,
    val isCompleted: Boolean,
    val idOwner : Long,
)
