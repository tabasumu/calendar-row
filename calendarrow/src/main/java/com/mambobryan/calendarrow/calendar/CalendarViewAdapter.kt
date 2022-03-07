package com.mambobryan.calendarrow.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mambobryan.library.calendarrow.databinding.ItemDateBinding
import com.mambobryan.calendarrow.utils.DateUtils
import java.util.*

class CalendarViewAdapter :
    ListAdapter<Date, CalendarViewAdapter.CalendarViewHolder>(DATE_COMPARATOR) {

    companion object {
        lateinit var selectionTracker: SelectionTracker<Long>
        private val DATE_COMPARATOR =
            object : DiffUtil.ItemCallback<Date>() {
                override fun areItemsTheSame(
                    oldItem: Date,
                    newItem: Date
                ): Boolean {
                    return oldItem.time == newItem.time
                }

                override fun areContentsTheSame(
                    oldItem: Date,
                    newItem: Date
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    init {
        setHasStableIds(true)
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

                tvCalendarDay.apply {
                    text = DateUtils.getDay3LettersName(date)
                    isSelected = selected
                }

                tvCalendarDate.apply {
                    text = DateUtils.getDayNumber(date)
                    isSelected = selected
                }

                layoutCalendar.apply {
                    isSelected = selected
                }

//                layoutCalendar.setBackgroundColor(Color.TRANSPARENT)
            }
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = absoluteAdapterPosition
                override fun getSelectionKey(): Long? = itemId
            }

    }


    override fun getItemViewType(position: Int): Int {
        return when (selectionTracker.isSelected(position.toLong())) {
            true -> {
                val selectedPosition = position + 1
                -selectedPosition
            }
            false -> {
                position
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CalendarViewHolder {

        val binding =
            ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(binding)

    }


    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {

        val date = getItem(position)
        holder.bind(date, selectionTracker.isSelected(position.toLong()))

    }

    override fun getItemCount() = currentList.size

    override fun getItemId(position: Int): Long = position.toLong()
}