package com.mambobryan.calendarrow.selection

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.mambobryan.calendarrow.calendar.CalendarViewAdapter


class CalendarDetailsLookup(private val recyclerView: RecyclerView) :
    ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as CalendarViewAdapter.CalendarViewHolder).getItemDetails()
        }
        return null
    }
}