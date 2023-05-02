package com.example.diaryactivity.ui.pickers

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.diaryactivity.utils.NotifyWork.Companion.NOTIFICATION_ID
import com.example.diaryactivity.utils.scheduleNotification
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId


@Composable
fun DateTimePicker(
    initialDateTime: LocalDateTime,
    onDateTimeSelected: (LocalDateTime) -> Unit
) {
    var localDateTimeState by rememberSaveable { mutableStateOf(initialDateTime) }
    val context = LocalContext.current
    val time = localDateTimeState.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    val diff = time - System.currentTimeMillis()

    val onDateSelected = { newDate: LocalDate ->
        val newDateTime = LocalDateTime.of(newDate, localDateTimeState.toLocalTime())
        localDateTimeState = newDateTime
        onDateTimeSelected(newDateTime)
    }

    val onTimeSelected = { newTime: LocalTime ->
        val newDateTime = LocalDateTime.of(localDateTimeState.toLocalDate(), newTime)
        localDateTimeState = newDateTime
        onDateTimeSelected(newDateTime)
    }

    Column {
        DatePicker(
            label = "",
            value = "",
            onSaveDate = onDateSelected,
            d = localDateTimeState.toLocalDate()
        )
        TimePicker(
            label = "",
            value = "",
            onSaveTime = onTimeSelected,
            t = localDateTimeState.toLocalTime(),
            onValueChange = {}
        )
        TextButton(onClick = { scheduleNotification(context = context,NOTIFICATION_ID, delay = diff) }) {
            Text("Schedule notification")
        }
    }
}
