package com.village.gramavaxi.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animals")
data class Animal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val type: String, // Sheep, Goat, Cow, Buffalo
    val breed: String,
    val age: Int, // in months
    val photoPath: String? = null,
    val tagNumber: String, // Unique identifier
    val registrationDate: Long = System.currentTimeMillis(),
    val ownerName: String,
    val ownerPhone: String
)