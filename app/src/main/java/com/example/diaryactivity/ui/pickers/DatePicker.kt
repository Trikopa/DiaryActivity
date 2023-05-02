package com.example.diaryactivity.ui.pickers

import android.app.DatePickerDialog
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
import java.time.format.DateTimeFormatter

@Composable
fun DatePicker(
    label: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    d: LocalDate,
    onSaveDate: (LocalDate) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    pattern: String = "yyyy-MM-dd",
) {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date = if (value.isNotBlank()) LocalDate.parse(value, formatter) else LocalDate.now()
    val dialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            onValueChange(LocalDate.of(year, month, dayOfMonth).toString())

        },
        date.year,
        date.monthValue,
        date.dayOfMonth,
    ).apply {
        setOnDateSetListener { _, year, month, dayOfMonth ->
            onSaveDate(LocalDate.of(year, month, dayOfMonth))
        }
    }
    Row {
        Text(text = d.toString())
        Spacer(modifier = Modifier.padding(end = 8.dp))
        TextButton(onClick = { dialog.show() }) {

            Text("show")

        }
    }
}