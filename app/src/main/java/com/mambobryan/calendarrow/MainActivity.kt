package com.mambobryan.calendarrow

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import com.google.android.material.snackbar.Snackbar
import com.mambobryan.calendarrow.databinding.ActivityMainBinding
import com.mambobryan.calendarrow.extensions.onDateSelected
import com.mambobryan.calendarrow.utils.DateUtils
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.apply {
            tvMonth.setOnClickListener { openDateDialog() }
            calendar.init()
            calendar.onDateSelected { date ->
                Snackbar.make(binding.root, DateUtils.getDayName(date), Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun openDateDialog() {
        val today = Calendar.getInstance()

        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker, pickedYear: Int, pickedMonth: Int, pickedDay: Int ->

                val selectedDate = Calendar.getInstance()

                selectedDate.set(Calendar.YEAR, pickedYear)
                selectedDate.set(Calendar.MONTH, pickedMonth)
                selectedDate.set(Calendar.DAY_OF_MONTH, pickedDay)

                binding.apply {
                    val builder = StringBuilder()

                    builder.append("${DateUtils.getDay3LettersName(selectedDate.time)} ")
                    builder.append(DateUtils.getDayNumber(selectedDate.time))
                    builder.append(" , ")
                    builder.append("${DateUtils.getMonthName(selectedDate.time)} ")
                    builder.append(DateUtils.getYear(selectedDate.time))

                    tvMonth.text = builder.toString()
                    calendar.setDate(selectedDate.time)
//                    calendar.setDates(listOf(selectedDate.time))
                }

            }, year, month, day
        )
        datePickerDialog.show()
    }
}