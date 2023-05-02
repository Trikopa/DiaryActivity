package com.example.diaryactivity.ui.pickers

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun TimePicker(
    label: String,
    value: String,
    onValueChange: (LocalTime) -> Unit,
    onSaveTime: (LocalTime) -> Unit,
    t: LocalTime,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    pattern: String = "HH:mm",
    is24HourView: Boolean = true,
) {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val time = if (value.isNotBlank()) LocalTime.parse(value, formatter) else LocalTime.now()
    val dialog = TimePickerDialog(
        LocalContext.current,
        { _, hour, minute ->
            onValueChange(LocalTime.of(hour, minute))
            onSaveTime(LocalTime.of(hour, minute))},
        time.hour,
        time.minute,
        is24HourView,
    )
    Row {
        Text(text = t.toString())
        Spacer(modifier = Modifier.padding(end = 8.dp))
        TextButton(onClick = { dialog.show() }) {
            Text("show")

        }
    }

}