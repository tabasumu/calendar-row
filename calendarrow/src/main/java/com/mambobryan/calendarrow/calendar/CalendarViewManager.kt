package com.mambobryan.calendarrow.calendar

import com.mambobryan.calendarrow.calendar.CalendarViewAdapter
import java.util.*

interface CalendarViewManager {

    /**
     * @param position of specific view in the SingleRowCalendar
     * @param date of specific view in the SingleRowCalendar
     * @param isSelected returns true if item in the SingleRowCalendar is selected else returns false
     * @return a resource id for SingleRowCalendar itemView
     */
    fun setCalendarViewResourceId(position: Int, date: Date, isSelected: Boolean): Int

    /**
     * @param holder is CalendarViewHolder used in SingleRowCalendar
     * @param date value of SingleRowCalendar item
     * @param position of specific view in the SingleRowCalendar
     * @param isSelected returns true if item in the SingleRowCalendar is selected else returns false
     */
    fun bindDataToCalendarView(
        holder: CalendarViewAdapter.CalendarViewHolder,
        date: Date,
        position: Int,
        isSelected: Boolean
    )

}
