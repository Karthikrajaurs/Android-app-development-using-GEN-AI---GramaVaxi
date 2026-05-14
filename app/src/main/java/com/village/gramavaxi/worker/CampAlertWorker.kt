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

class CampAlertWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val database = AppDatabase.getDatabase(applicationContext)
            val campDao = database.vaccinationCampDao()

            // Get camps happening in next 3 days
            val calendar = Calendar.getInstance()
            val currentTime = calendar.timeInMillis

            calendar.add(Calendar.DAY_OF_MONTH, 3)
            val threeDaysLater = calendar.timeInMillis

            val upcomingCamps = campDao.getCampsInRange(currentTime, threeDaysLater)

            // Send loud notifications for each camp
            upcomingCamps.forEach { camp ->
                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                val campDate = dateFormat.format(Date(camp.campDate))

                NotificationHelper.showCampAlert(
                    applicationContext,
                    camp.location,
                    campDate,
                    "${camp.startTime} - ${camp.endTime}",
                    camp.id.toInt()
                )
            }

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}