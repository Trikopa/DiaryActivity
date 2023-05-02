package com.example.diaryactivity.utils


import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.Color.RED
import android.media.AudioAttributes
import android.media.AudioAttributes.CONTENT_TYPE_SONIFICATION
import android.media.AudioAttributes.USAGE_NOTIFICATION_RINGTONE
import android.media.RingtoneManager.TYPE_NOTIFICATION
import android.media.RingtoneManager.getDefaultUri
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.S
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.DEFAULT_ALL
import androidx.core.app.NotificationCompat.PRIORITY_MAX
import androidx.work.Data
import androidx.work.ListenableWorker.Result.success
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.diaryactivity.MainActivity
import com.example.diaryactivity.utils.NotifyWork.Companion.NOTIFICATION_ID
import java.util.concurrent.TimeUnit


class NotifyWork(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        val id = inputData.getLong(NOTIFICATION_ID, 0).toInt()
        sendNotification(id)

        return success()
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun sendNotification(id: Int) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra(NOTIFICATION_ID, id)

        val notificationManager =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val titleNotification = "notification"
        val subtitleNotification = "subtitle"
        val pendingIntent = if (SDK_INT >= S) {
            getActivity(applicationContext, 0, intent, PendingIntent.FLAG_MUTABLE)
        } else {
            getActivity(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
            .setSmallIcon(androidx.drawerlayout.R.drawable.notification_action_background)
            .setContentTitle(titleNotification).setContentText(subtitleNotification)
            .setDefaults(DEFAULT_ALL).setContentIntent(pendingIntent).setAutoCancel(true)

        notification.priority = PRIORITY_MAX

        notification.setChannelId(NOTIFICATION_CHANNEL)

        val ringtoneManager = getDefaultUri(TYPE_NOTIFICATION)
        val audioAttributes = AudioAttributes.Builder().setUsage(USAGE_NOTIFICATION_RINGTONE)
            .setContentType(CONTENT_TYPE_SONIFICATION).build()

        val channel =
            NotificationChannel(NOTIFICATION_CHANNEL, NOTIFICATION_NAME, IMPORTANCE_HIGH)

        channel.enableLights(true)
        channel.lightColor = RED
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        channel.setSound(ringtoneManager, audioAttributes)
        notificationManager.createNotificationChannel(channel)

        notificationManager.notify(id, notification.build())
    }

    companion object {
        const val NOTIFICATION_ID = "appName_notification_id"
        const val NOTIFICATION_NAME = "appName"
        const val NOTIFICATION_CHANNEL = "appName_channel_01"
        const val NOTIFICATION_WORK = "appName_notification_work"
    }
}

fun scheduleNotification(context: Context, notificationId: String, delay: Long) {
    val inputData = Data.Builder().putString(NOTIFICATION_ID, notificationId).build()
    val notifyWorkRequest = OneTimeWorkRequestBuilder<NotifyWork>()
        .setInputData(inputData)
        .setInitialDelay(delay, TimeUnit.MILLISECONDS)
        .build()
    WorkManager.getInstance(context).enqueue(notifyWorkRequest)
}


