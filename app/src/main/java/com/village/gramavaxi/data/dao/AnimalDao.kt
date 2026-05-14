package com.village.gramavaxi.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.village.gramavaxi.data.entity.Animal

@Dao
interface AnimalDao {

    @Insert
    suspend fun insert(animal: Animal): Long

    @Update
    suspend fun update(animal: Animal)

    @Delete
    suspend fun delete(animal: Animal)

    @Query("SELECT * FROM animals ORDER BY registrationDate DESC")
    fun getAllAnimals(): LiveData<List<Animal>>

    @Query("SELECT * FROM animals WHERE id = :animalId")
    fun getAnimalById(animalId: Long): LiveData<Animal>

    @Query("SELECT * FROM animals WHERE type = :type ORDER BY name ASC")
    fun getAnimalsByType(type: String): LiveData<List<Animal>>

    @Query("SELECT COUNT(*) FROM animals")
    suspend fun getAnimalCount(): Int
}