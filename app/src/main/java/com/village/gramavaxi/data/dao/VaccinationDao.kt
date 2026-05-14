package com.village.gramavaxi.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.village.gramavaxi.data.entity.Vaccination

@Dao
interface VaccinationDao {

    @Insert
    suspend fun insert(vaccination: Vaccination): Long

    @Update
    suspend fun update(vaccination: Vaccination)

    @Delete
    suspend fun delete(vaccination: Vaccination)

    @Query("SELECT * FROM vaccinations WHERE animalId = :animalId ORDER BY administeredDate DESC")
    fun getVaccinationsByAnimal(animalId: Long): LiveData<List<Vaccination>>

    @Query("SELECT * FROM vaccinations WHERE nextDueDate BETWEEN :startDate AND :endDate AND isCompleted = 0")
    suspend fun getUpcomingVaccinations(startDate: Long, endDate: Long): List<Vaccination>

    @Query("SELECT * FROM vaccinations WHERE nextDueDate < :currentDate AND isCompleted = 0")
    suspend fun getOverdueVaccinations(currentDate: Long): List<Vaccination>

    @Query("SELECT * FROM vaccinations ORDER BY nextDueDate ASC LIMIT 10")
    fun getNextVaccinations(): LiveData<List<Vaccination>>
}