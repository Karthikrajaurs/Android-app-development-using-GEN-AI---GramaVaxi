package com.village.gramavaxi.utils

import java.util.*

object VaccinationScheduler {

    data class VaccineSchedule(
        val name: String,
        val monthsFromBirth: Int,
        val type: String
    )

    // Standard vaccination schedule for livestock
    private val sheepGoatSchedule = listOf(
        VaccineSchedule("PPR (Peste des Petits Ruminants)", 3, "PPR"),
        VaccineSchedule("PPR Booster", 15, "PPR"),
        VaccineSchedule("Foot and Mouth Disease (FMD)", 4, "FMD"),
        VaccineSchedule("FMD Booster 1", 10, "FMD"),
        VaccineSchedule("FMD Booster 2", 16, "FMD"),
        VaccineSchedule("Haemorrhagic Septicaemia (HS)", 6, "HS"),
        VaccineSchedule("HS Annual Booster", 18, "HS"),
        VaccineSchedule("Anthrax", 6, "Anthrax"),
        VaccineSchedule("Anthrax Annual", 18, "Anthrax")
    )

    private val cattleSchedule = listOf(
        VaccineSchedule("FMD Primary", 4, "FMD"),
        VaccineSchedule("FMD Booster 1", 10, "FMD"),
        VaccineSchedule("FMD Booster 2", 16, "FMD"),
        VaccineSchedule("HS Primary", 6, "HS"),
        VaccineSchedule("HS Annual", 18, "HS"),
        VaccineSchedule("BQ (Black Quarter)", 6, "BQ"),
        VaccineSchedule("BQ Annual", 18, "BQ"),
        VaccineSchedule("Anthrax", 6, "Anthrax")
    )

    fun getScheduleForAnimal(animalType: String, birthDate: Long): List<Pair<VaccineSchedule, Long>> {
        val schedule = when (animalType.lowercase()) {
            "sheep", "goat" -> sheepGoatSchedule
            "cow", "buffalo", "cattle" -> cattleSchedule
            else -> sheepGoatSchedule
        }

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = birthDate

        return schedule.map { vaccine ->
            val dueDate = Calendar.getInstance()
            dueDate.timeInMillis = birthDate
            dueDate.add(Calendar.MONTH, vaccine.monthsFromBirth)

            vaccine to dueDate.timeInMillis
        }
    }

    fun getNextVaccinationDate(lastVaccinationDate: Long, vaccineType: String): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = lastVaccinationDate

        // Default intervals (can be customized)
        val monthsToAdd = when (vaccineType.uppercase()) {
            "FMD" -> 6
            "PPR" -> 12
            "HS" -> 12
            "BQ" -> 12
            "ANTHRAX" -> 12
            else -> 12
        }

        calendar.add(Calendar.MONTH, monthsToAdd)
        return calendar.timeInMillis
    }
}