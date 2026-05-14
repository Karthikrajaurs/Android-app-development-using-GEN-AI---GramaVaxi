package com.village.gramavaxi.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    private val dateTimeFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())

    fun formatDate(timestamp: Long): String {
        return dateFormat.format(Date(timestamp))
    }

    fun formatTime(timestamp: Long): String {
        return timeFormat.format(Date(timestamp))
    }

    fun formatDateTime(timestamp: Long): String {
        return dateTimeFormat.format(Date(timestamp))
    }

    fun getCalendarFromTimestamp(timestamp: Long): Calendar {
        return Calendar.getInstance().apply {
            timeInMillis = timestamp
        }
    }

    fun getDaysDifference(startTime: Long, endTime: Long): Long {
        val diff = endTime - startTime
        return diff / (1000 * 60 * 60 * 24)
    }

    fun isOverdue(dueDate: Long): Boolean {
        return dueDate < System.currentTimeMillis()
    }

    fun addMonths(timestamp: Long, months: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        calendar.add(Calendar.MONTH, months)
        return calendar.timeInMillis
    }
}