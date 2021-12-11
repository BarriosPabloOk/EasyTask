package com.pablobarriosdevs.easytask.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class SubTask(
    @PrimaryKey(autoGenerate = true) val idSubTask : Int,
    val title: String,
    val isCompleted: Boolean,
    val idOwnerTask : Int,
)
