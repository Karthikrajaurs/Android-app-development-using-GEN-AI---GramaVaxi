package com.village.gramavaxi.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "disease_reports",
    foreignKeys = [
        ForeignKey(
            entity = Animal::class,
            parentColumns = ["id"],
            childColumns = ["animalId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["animalId"])]
)
data class DiseaseReport(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val animalId: Long,
    val symptoms: String,
    val reportDate: Long = System.currentTimeMillis(),
    val severity: String,
    val vetNotified: Boolean = false,
    val vetResponse: String? = null,
    val status: String = "PENDING",
    val photoPath: String? = null
)