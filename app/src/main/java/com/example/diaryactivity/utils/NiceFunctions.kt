package com.example.diaryactivity.utils

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.diaryactivity.utils.NotifyWork.Companion.NOTIFICATION_WORK
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.concurrent.TimeUnit



fun formattedDate(time: Long): String {
    val instant = Instant.ofEpochMilli(time)
    val zoneId = ZoneId.systemDefault()
    val dateTime = LocalDateTime.ofInstant(instant, zoneId)
    val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a", Locale.getDefault())
    return dateTime.format(formatter)
}

