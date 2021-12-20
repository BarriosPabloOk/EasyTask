package com.pablobarriosdevs.easytask.domain.use_cases

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class RowCalendarUseCase @Inject constructor(

) {

    val cal = Calendar.getInstance()

    suspend operator fun invoke(addWeek : Int = 0) : List<Date>{
        val monthlyCalendar = cal.clone() as Calendar
        val dates = mutableListOf<Date>()
        monthlyCalendar.set(Calendar.DAY_OF_WEEK, 1)
        val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_WEEK)
        dates.clear()

        while (dates.size < maxDaysInMonth){
            dates.add(monthlyCalendar.time)
            monthlyCalendar.add(Calendar.DAY_OF_WEEK,1)
        }
        if (addWeek != 0) cal.add(Calendar.DAY_OF_WEEK, addWeek)

        return dates
    }
}