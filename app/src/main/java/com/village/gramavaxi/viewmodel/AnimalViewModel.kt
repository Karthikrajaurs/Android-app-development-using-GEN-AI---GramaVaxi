package com.village.gramavaxi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.village.gramavaxi.data.database.AppDatabase
import com.village.gramavaxi.data.entity.*
import com.village.gramavaxi.repository.AnimalRepository
import com.village.gramavaxi.utils.VaccinationScheduler
import kotlinx.coroutines.launch

class AnimalViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AnimalRepository
    val allAnimals: LiveData<List<Animal>>
    val upcomingCamps: LiveData<List<VaccinationCamp>>
    val nextVaccinations: LiveData<List<Vaccination>>
    val pendingReports: LiveData<List<DiseaseReport>>

    init {
        val database = AppDatabase.getDatabase(application)
        repository = AnimalRepository(
            database.animalDao(),
            database.vaccinationDao(),
            database.vaccinationCampDao(),
            database.diseaseReportDao()
        )
        allAnimals = repository.allAnimals
        upcomingCamps = repository.upcomingCamps
        nextVaccinations = repository.nextVaccinations
        pendingReports = repository.pendingReports
    }

    fun insertAnimal(animal: Animal) = viewModelScope.launch {
        repository.insertAnimal(animal)
    }

    fun updateAnimal(animal: Animal) = viewModelScope.launch {
        repository.updateAnimal(animal)
    }

    fun deleteAnimal(animal: Animal) = viewModelScope.launch {
        repository.deleteAnimal(animal)
    }

    fun getAnimalById(id: Long): LiveData<Animal> {
        return repository.getAnimalById(id)
    }

    fun insertVaccination(vaccination: Vaccination) = viewModelScope.launch {
        repository.insertVaccination(vaccination)
    }

    fun getVaccinationsByAnimal(animalId: Long): LiveData<List<Vaccination>> {
        return repository.getVaccinationsByAnimal(animalId)
    }

    fun insertCamp(camp: VaccinationCamp) = viewModelScope.launch {
        repository.insertCamp(camp)
    }

    fun insertDiseaseReport(report: DiseaseReport) = viewModelScope.launch {
        repository.insertDiseaseReport(report)
    }

    fun getReportsByAnimal(animalId: Long): LiveData<List<DiseaseReport>> {
        return repository.getReportsByAnimal(animalId)
    }

    fun insertAnimalAndScheduleVaccinations(
        animal: Animal,
        onComplete: (Long) -> Unit
    ) = viewModelScope.launch {
        val animalId = repository.insertAnimal(animal)

        val birthDate = System.currentTimeMillis() -
                (animal.age.toLong() * 30L * 24L * 60L * 60L * 1000L)

        val schedule = VaccinationScheduler.getScheduleForAnimal(animal.type, birthDate)

        val currentTime = System.currentTimeMillis()
        schedule.forEach { (vaccineSchedule, dueDate) ->
            if (dueDate > currentTime) {
                val vaccination = Vaccination(
                    animalId = animalId,
                    vaccineName = vaccineSchedule.name,
                    vaccinationType = vaccineSchedule.type,
                    administeredDate = currentTime,
                    nextDueDate = dueDate,
                    campLocation = "Village Veterinary Camp",
                    isCompleted = false
                )
                repository.insertVaccination(vaccination)
            }
        }
        onComplete(animalId)
    }
}