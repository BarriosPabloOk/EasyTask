package com.pablobarriosdevs.easytask.domain.model.relations

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.pablobarriosdevs.easytask.domain.model.SubTask
import com.pablobarriosdevs.easytask.domain.model.Task

@Entity
data class TaskWithSubTasks(
    @Embedded
    val task : Task,
    @Relation(
        parentColumn = "idTask",
        entityColumn = "idOwner"
    )
    val subTasks : List<SubTask>,
)
