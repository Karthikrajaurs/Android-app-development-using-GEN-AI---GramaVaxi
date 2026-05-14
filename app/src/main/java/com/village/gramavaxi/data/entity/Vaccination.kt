package com.village.gramavaxi.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vaccinations",
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
data class Vaccination(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val animalId: Long,
    val vaccineName: String,
    val vaccinationType: String,
    val administeredDate: Long,
    val nextDueDate: Long,
    val campLocation: String,
    val veterinarianName: String? = null,
    val batchNumber: String? = null,
    val notes: String? = null,
    val isCompleted: Boolean = true
)