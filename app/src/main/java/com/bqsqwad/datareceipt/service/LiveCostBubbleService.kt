package com.bqsqwad.datareceipt.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.bqsqwad.datareceipt.R
import com.bqsqwad.datareceipt.ui.MainActivity

/**
 * Foreground service for the Live Cost Bubble.
 * Currently a stable stub so the project builds cleanly.
 * Full TrafficStats sampling will be implemented next.
 */
class LiveCostBubbleService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val notification = buildNotification()
        startForeground(NOTIFICATION_ID, notification)
        return START_STICKY
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Live Cost Bubble",
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = "Shows live data cost while the bubble is active"
        }
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    private fun buildNotification(): Notification {
        val openIntent = Intent(this, MainActivity::class.java)
        val pending = PendingIntent.getActivity(
            this, 0, openIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Live Cost Bubble")
            .setContentText("Monitoring data cost…")
            .setSmallIcon(R.drawable.ic_tile_live_cost)
            .setContentIntent(pending)
            .setOngoing(true)
            .build()
    }

    companion object {
        const val CHANNEL_ID = "live_cost_bubble"
        const val NOTIFICATION_ID = 1001
    }
}
