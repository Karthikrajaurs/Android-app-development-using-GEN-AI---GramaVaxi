package com.village.gramavaxi.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.village.gramavaxi.data.dao.*
import com.village.gramavaxi.data.entity.*

@Database(
    entities = [
        Animal::class,
        Vaccination::class,
        VaccinationCamp::class,
        DiseaseReport::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun animalDao(): AnimalDao
    abstract fun vaccinationDao(): VaccinationDao
    abstract fun vaccinationCampDao(): VaccinationCampDao
    abstract fun diseaseReportDao(): DiseaseReportDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gramavaxi_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}