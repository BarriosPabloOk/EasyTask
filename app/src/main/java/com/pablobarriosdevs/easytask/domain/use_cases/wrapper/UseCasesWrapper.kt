package com.pablobarriosdevs.easytask.domain.use_cases.wrapper

import com.pablobarriosdevs.easytask.domain.use_cases.*

data class UseCasesWrapper(
    val insertTaskUseCase: InsertTaskUseCase,
    val insertSubTaskUseCase: InsertSubTaskUseCase,
    val getAllTasksByTargetDateUseCase: GetAllTasksByTodayDateUseCase,
    val getSingleTaskWithSubTaskUseCase: GetSingleTaskWithSubTaskUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase,
    val searchTaskUseCase: SearchTaskUseCase,
    val deleteSubTaskUseCase: DeleteSubTaskUseCase,
    val rowCalendarUseCase: RowCalendarUseCase,
)
