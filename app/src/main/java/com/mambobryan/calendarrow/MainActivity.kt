package com.mambobryan.calendarrow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.mambobryan.calendarrow.databinding.ActivityMainBinding
import com.mambobryan.calendarrow.extensions.onDateSelected
import com.mambobryan.calendarrow.utils.DateUtils

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
            calendar.init()
            calendar.onDateSelected { date ->
                Snackbar.make(binding.root, DateUtils.getDayName(date), Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }
}