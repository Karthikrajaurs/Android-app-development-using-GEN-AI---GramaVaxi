package com.village.gramavaxi.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.village.gramavaxi.data.entity.VaccinationCamp

@Dao
interface VaccinationCampDao {

    @Insert
    suspend fun insert(camp: VaccinationCamp): Long

    @Update
    suspend fun update(camp: VaccinationCamp)

    @Delete
    suspend fun delete(camp: VaccinationCamp)

    @Query("SELECT * FROM vaccination_camps WHERE campDate >= :currentDate ORDER BY campDate ASC")
    fun getUpcomingCamps(currentDate: Long): LiveData<List<VaccinationCamp>>

    @Query("SELECT * FROM vaccination_camps WHERE campDate BETWEEN :startDate AND :endDate")
    suspend fun getCampsInRange(startDate: Long, endDate: Long): List<VaccinationCamp>

    @Query("SELECT * FROM vaccination_camps ORDER BY campDate DESC")
    fun getAllCamps(): LiveData<List<VaccinationCamp>>
}