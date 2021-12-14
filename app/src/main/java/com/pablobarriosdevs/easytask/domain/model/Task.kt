package com.pablobarriosdevs.easytask.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Task(
    @PrimaryKey val idTask : Int? = null,
    val title : String,
    val description : String,
    val createdDate : Long,
    val todayDate : Long,
    val isCompleted : Boolean,
    val priority : Int,
    val priorityString : String,
)
