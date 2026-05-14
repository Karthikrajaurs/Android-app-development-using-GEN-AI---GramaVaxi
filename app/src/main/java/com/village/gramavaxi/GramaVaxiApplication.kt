package com.village.gramavaxi

import android.app.Application
import androidx.work.*
import com.village.gramavaxi.utils.NotificationHelper
import com.village.gramavaxi.worker.CampAlertWorker
import com.village.gramavaxi.worker.VaccinationReminderWorker
import java.util.concurrent.TimeUnit

class GramaVaxiApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Create notification channels
        NotificationHelper.createNotificationChannels(this)

        // Schedule periodic work for vaccination reminders
        scheduleVaccinationReminders()

        // Schedule periodic work for camp alerts
        scheduleCampAlerts()
    }

    private fun scheduleVaccinationReminders() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresBatteryNotLow(false)
            .build()

        val reminderWork = PeriodicWorkRequestBuilder<VaccinationReminderWorker>(
            24, TimeUnit.HOURS, // Repeat every 24 hours
            30, TimeUnit.MINUTES  // Flex interval
        )
            .setConstraints(constraints)
            .setInitialDelay(1, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "vaccination_reminders",
            ExistingPeriodicWorkPolicy.KEEP,
            reminderWork
        )
    }

    private fun scheduleCampAlerts() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresBatteryNotLow(false)
            .build()

        val campWork = PeriodicWorkRequestBuilder<CampAlertWorker>(
            12, TimeUnit.HOURS, // Check twice daily
            30, TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .setInitialDelay(30, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "camp_alerts",
            ExistingPeriodicWorkPolicy.KEEP,
            campWork
        )
    }
}