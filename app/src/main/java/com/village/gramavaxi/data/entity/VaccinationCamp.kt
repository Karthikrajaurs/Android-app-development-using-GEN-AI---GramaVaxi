package com.village.gramavaxi.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vaccination_camps")
data class VaccinationCamp(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val campDate: Long, // timestamp
    val location: String,
    val vaccineTypes: String, // Comma-separated list
    val startTime: String,
    val endTime: String,
    val veterinarianName: String,
    val contactNumber: String,
    val notificationSent: Boolean = false,
    val notes: String? = null
)