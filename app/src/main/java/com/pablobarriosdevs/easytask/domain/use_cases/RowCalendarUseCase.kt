package com.pablobarriosdevs.easytask.domain.use_cases

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class RowCalendarUseCase @Inject constructor(

) {

    suspend operator fun invoke(calendar: Calendar) : List<Date>{

        val list = mutableListOf<Date>()

        for (i in 0 until Calendar.DAY_OF_WEEK) {
            val setStart = calendar.set(Calendar.DAY_OF_WEEK, 1)
            val days = calendar.add(Calendar.DAY_OF_WEEK, i)

            list.add(calendar.time)
        }
        return list
    }
}