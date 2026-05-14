package com.village.gramavaxi.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.village.gramavaxi.data.database.AppDatabase
import com.village.gramavaxi.utils.NotificationHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class VaccinationReminderWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val database = AppDatabase.getDatabase(applicationContext)
            val vaccinationDao = database.vaccinationDao()
            val animalDao = database.animalDao()

            // Get current time
            val calendar = Calendar.getInstance()
            val currentTime = calendar.timeInMillis

            // Get vaccinations due in next 3 days
            calendar.add(Calendar.DAY_OF_MONTH, 3)
            val threeDaysLater = calendar.timeInMillis

            val upcomingVaccinations = vaccinationDao.getUpcomingVaccinations(
                currentTime,
                threeDaysLater
            )

            // Send notifications for each upcoming vaccination
            upcomingVaccinations.forEach { vaccination ->
                val animal = animalDao.getAnimalById(vaccination.animalId)

                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                val dueDate = dateFormat.format(Date(vaccination.nextDueDate))

                val title = "Vaccination Reminder | ಲಸಿಕೆ ಜ್ಞಾಪನೆ"
                val message = "Animal: ${vaccination.vaccineName}\n" +
                        "Vaccine: ${vaccination.vaccinationType}\n" +
                        "Due Date: $dueDate\n" +
                        "Location: ${vaccination.campLocation}"

                NotificationHelper.showVaccinationReminder(
                    applicationContext,
                    title,
                    message,
                    vaccination.id.toInt()
                )
            }

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}