package com.mambobryan.calendarrow.selection

import java.util.*

interface CalendarSelectionManager {

    /**
     * Using this function we can enable or disable selection for particular item
     * @param position of specific item in a calendar
     * @param date specific date used in calendar on this position
     * @return true when item can be selected, else returns false
     */
    fun canBeItemSelected(position: Int, date: Date): Boolean
}
