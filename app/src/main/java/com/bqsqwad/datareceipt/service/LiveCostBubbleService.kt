package com.bqsqwad.datareceipt.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView
import androidx.core.app.NotificationCompat
import com.bqsqwad.datareceipt.R
import com.bqsqwad.datareceipt.ui.MainActivity
import java.util.Locale

class LiveCostBubbleService : Service() {
    private lateinit var windowManager: WindowManager
    private var bubble: TextView? = null
    private val handler = Handler(Looper.getMainLooper())
    private var startingBytes = 0L

    private val ticker = object : Runnable {
        override fun run() {
            val total = android.net.TrafficStats.getTotalRxBytes() +
                android.net.TrafficStats.getTotalTxBytes()
            val used = (total - startingBytes).coerceAtLeast(0L)
            val mb = used / (1024.0 * 1024.0)
            bubble?.text = String.format(Locale.getDefault(), "BQ LIVE\n%.2f MB", mb)
            handler.postDelayed(this, 1000L)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_STOP) {
            stopSelf()
            return START_NOT_STICKY
        }
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, buildNotification())
        if (bubble == null) {
            windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
            startingBytes = android.net.TrafficStats.getTotalRxBytes() +
                android.net.TrafficStats.getTotalTxBytes()
            showBubble()
            handler.post(ticker)
        }
        return START_STICKY
    }

    private fun showBubble() {
        val view = TextView(this).apply {
            text = "BQ LIVE\n0.00 MB"
            textSize = 12f
            setTextColor(android.graphics.Color.WHITE)
            setPadding(24, 16, 24, 16)
            setBackgroundColor(android.graphics.Color.rgb(24, 83, 145))
            elevation = 12f
        }
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.END
            x = 20
            y = 180
        }
        windowManager.addView(view, params)
        bubble = view
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID, "Live Cost Bubble", NotificationManager.IMPORTANCE_LOW
        ).apply { description = "Shows mobile and Wi-Fi data transferred during this session" }
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }

    private fun buildNotification(): Notification {
        val openIntent = PendingIntent.getActivity(
            this, 0, Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val stopIntent = PendingIntent.getService(
            this, 1, Intent(this, LiveCostBubbleService::class.java).setAction(ACTION_STOP),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Live data session")
            .setContentText("Tracking total device data transferred during this session")
            .setSmallIcon(R.drawable.ic_tile_live_cost)
            .setContentIntent(openIntent)
            .addAction(0, "Stop", stopIntent)
            .setOngoing(true)
            .build()
    }

    override fun onDestroy() {
        handler.removeCallbacks(ticker)
        bubble?.let { if (::windowManager.isInitialized) windowManager.removeView(it) }
        bubble = null
        super.onDestroy()
    }

    companion object {
        const val CHANNEL_ID = "live_cost_bubble"
        const val NOTIFICATION_ID = 1001
        const val ACTION_STOP = "com.bqsqwad.datareceipt.action.STOP_LIVE_BUBBLE"
    }
}