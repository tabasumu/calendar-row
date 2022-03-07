package com.mambobryan.calendarrow.extensions

import com.mambobryan.calendarrow.calendar.CalendarRowView
import java.util.*

inline fun CalendarRowView.onDateSelected(crossinline listener: (Date) -> Unit) {
    this.setOnDateChangeListener(object : CalendarRowView.OnDateChangeListener {
        override fun onDateChanged(isSelected: Boolean, date: Date) {
            if (isSelected) listener(date)
            return
        }
    })
}

inline fun CalendarRowView.onDateChanged(crossinline listener: (Boolean, Date) -> Unit) {
    this.setOnDateChangeListener(object : CalendarRowView.OnDateChangeListener {
        override fun onDateChanged(isSelected: Boolean, date: Date) {
            listener(isSelected, date)
            return
        }
    })
}

fun CalendarRowView.onCalendarChanged(
    selected: ((date: Date) -> Unit)? = null,
    changed: ((selected: Boolean, date: Date) -> Unit)? = null
) {
    this.setOnDateChangeListener(object : CalendarRowView.OnDateChangeListener {
        override fun onDateChanged(isSelected: Boolean, date: Date) {
            if (isSelected) selected?.let { it(date) }
            changed?.let { it(isSelected, date) }
            return
        }
    })
}