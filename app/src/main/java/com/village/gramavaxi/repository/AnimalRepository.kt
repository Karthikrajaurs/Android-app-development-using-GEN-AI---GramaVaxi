package com.village.gramavaxi.repository

import androidx.lifecycle.LiveData
import com.village.gramavaxi.data.dao.*
import com.village.gramavaxi.data.entity.*

class AnimalRepository(
    private val animalDao: AnimalDao,
    private val vaccinationDao: VaccinationDao,
    private val campDao: VaccinationCampDao,
    private val diseaseReportDao: DiseaseReportDao
) {

    // Animals
    val allAnimals: LiveData<List<Animal>> = animalDao.getAllAnimals()

    suspend fun insertAnimal(animal: Animal): Long {
        return animalDao.insert(animal)
    }

    suspend fun updateAnimal(animal: Animal) {
        animalDao.update(animal)
    }

    suspend fun deleteAnimal(animal: Animal) {
        animalDao.delete(animal)
    }

    fun getAnimalById(id: Long): LiveData<Animal> {
        return animalDao.getAnimalById(id)
    }

    fun getAnimalsByType(type: String): LiveData<List<Animal>> {
        return animalDao.getAnimalsByType(type)
    }

    // Vaccinations
    suspend fun insertVaccination(vaccination: Vaccination): Long {
        return vaccinationDao.insert(vaccination)
    }

    fun getVaccinationsByAnimal(animalId: Long): LiveData<List<Vaccination>> {
        return vaccinationDao.getVaccinationsByAnimal(animalId)
    }

    suspend fun getUpcomingVaccinations(startDate: Long, endDate: Long): List<Vaccination> {
        return vaccinationDao.getUpcomingVaccinations(startDate, endDate)
    }

    val nextVaccinations: LiveData<List<Vaccination>> = vaccinationDao.getNextVaccinations()

    // Camps
    suspend fun insertCamp(camp: VaccinationCamp): Long {
        return campDao.insert(camp)
    }

//    val upcomingCamps: LiveData<List<VaccinationCamp>> = campDao.getUpcomingCamps()
    val upcomingCamps: LiveData<List<VaccinationCamp>> =
        campDao.getUpcomingCamps(System.currentTimeMillis())
    suspend fun getCampsInRange(startDate: Long, endDate: Long): List<VaccinationCamp> {
        return campDao.getCampsInRange(startDate, endDate)
    }

    // Disease Reports
    suspend fun insertDiseaseReport(report: DiseaseReport): Long {
        return diseaseReportDao.insert(report)
    }

    fun getReportsByAnimal(animalId: Long): LiveData<List<DiseaseReport>> {
        return diseaseReportDao.getReportsByAnimal(animalId)
    }

    val pendingReports: LiveData<List<DiseaseReport>> = diseaseReportDao.getPendingReports()
}