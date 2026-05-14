package com.village.gramavaxi.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.village.gramavaxi.data.entity.DiseaseReport

@Dao
interface DiseaseReportDao {

    @Insert
    suspend fun insert(report: DiseaseReport): Long

    @Update
    suspend fun update(report: DiseaseReport)

    @Delete
    suspend fun delete(report: DiseaseReport)

    @Query("SELECT * FROM disease_reports WHERE animalId = :animalId ORDER BY reportDate DESC")
    fun getReportsByAnimal(animalId: Long): LiveData<List<DiseaseReport>>

    @Query("SELECT * FROM disease_reports WHERE status = 'PENDING' ORDER BY reportDate DESC")
    fun getPendingReports(): LiveData<List<DiseaseReport>>

    @Query("SELECT * FROM disease_reports ORDER BY reportDate DESC")
    fun getAllReports(): LiveData<List<DiseaseReport>>
}