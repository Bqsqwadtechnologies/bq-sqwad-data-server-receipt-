package com.bqsqwad.datareceipt.service

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.bqsqwad.datareceipt.R
import com.bqsqwad.datareceipt.ui.MainActivity
import java.util.concurrent.TimeUnit

class LiveBubbleReminderWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        // End the previous six-hour live session before asking the user to renew it.
        applicationContext.stopService(Intent(applicationContext, LiveCostBubbleService::class.java))
        createChannel()
        if (Build.VERSION.SDK_INT >= 33 &&
            applicationContext.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) return Result.success()

        val openIntent = Intent(applicationContext, MainActivity::class.java).apply {
            putExtra(MainActivity.EXTRA_OPEN_LIVE_BUBBLE, true)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            6106,
            openIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Attention: Check your data usage")
            .setContentText("Review which apps are using data and renew your 6-hour Live Bubble session.")
            .setStyle(
                NotificationCompat.BigTextStyle().bigText(
                    "Your data can be used while apps run in the background. Open BQ Sqwad Data Receipt to review available usage information, see data transferred and manage your bundle. Watch two short ads to renew the Live Bubble for another 6 hours."
                )
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        return try {
            NotificationManagerCompat.from(applicationContext).notify(NOTIFICATION_ID, notification)
            Result.success()
        } catch (_: SecurityException) {
            Result.success()
        }
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Data usage reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Six-hour reminders to review data usage and the Live Bubble"
                enableVibration(true)
            }
            applicationContext.getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "live_bubble_six_hour_reminders"
        const val NOTIFICATION_ID = 6106
        private const val UNIQUE_WORK = "live_bubble_six_hour_reminder"

        fun schedule(context: Context) {
            val request = PeriodicWorkRequestBuilder<LiveBubbleReminderWorker>(
                6, TimeUnit.HOURS
            ).build()
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                UNIQUE_WORK,
                ExistingPeriodicWorkPolicy.UPDATE,
                request
            )
        }
    }
}
