package com.mambobryan.calendarrow.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.mambobryan.library.calendarrow.databinding.ItemDateBinding
import com.mambobryan.calendarrow.utils.DateUtils
import java.util.*

class CalendarViewAdapter :
    RecyclerView.Adapter<CalendarViewAdapter.CalendarViewHolder>() {

    var textColor: Int? = null
    var selectedTextColor: Int? = null
    var backgroundColor: Int? = null
    var selectedBackgroundColor: Int? = null

    private var dates = listOf<Date>()

    companion object {
        lateinit var selectionTracker: SelectionTracker<Long>
    }

    init {
        setHasStableIds(true)
    }

    fun setList(list: List<Date>) {
        dates = list
        notifyDataSetChanged()
    }

    inner class CalendarViewHolder(private val binding: ItemDateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                layoutCalendarClick.setOnClickListener { }
            }
        }

        fun bind(date: Date, selected: Boolean) {
            binding.apply {

                val color = if (selected) selectedTextColor else textColor

                tvCalendarDay.apply {
                    text = DateUtils.getDay3LettersName(date)
                    isSelected = selected
                    color?.let { setTextColor(it) }
                }

                tvCalendarDate.apply {
                    text = DateUtils.getDayNumber(date)
                    isSelected = selected
                    color?.let { setTextColor(it) }
                }

                layoutCalendar.apply {
                    isSelected = selected
                    (if (selected) selectedBackgroundColor else backgroundColor)?.let {
                        setBackgroundColor(
                            it
                        )
                    }
                }

            }


        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = absoluteAdapterPosition
                override fun getSelectionKey(): Long? = itemId
            }

    }


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CalendarViewHolder {

        val binding =
            ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(binding)

    }


    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {

        val date = dates[position]
        holder.bind(date, selectionTracker.isSelected(position.toLong()))

    }

    override fun getItemCount() = dates.size

    override fun getItemId(position: Int): Long = position.toLong()
}