package com.example.diaryactivity.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.diaryactivity.model.entry.Entry
import com.example.diaryactivity.ui.pickers.DateTimePicker
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardPopup(
    onEditClick: (Entry) -> Unit = {},
    onDeleteClick: () -> Unit,
    dismiss: () -> Unit,
    text: String,
    id: Int,
    onChangeText: (String) -> Unit,
    simpleNotification: () -> Unit
) {
    var localDateTimeState by rememberSaveable {  mutableStateOf(LocalDateTime.now())}

    val setDateTime = {dateTime: LocalDateTime -> localDateTimeState = dateTime }
    AlertDialog(
        onDismissRequest = { dismiss() },
        title = { Text("Možnosti") },
        shape = MaterialTheme.shapes.medium,
        text = {
            Column {
                OutlinedTextField(value = text, onValueChange = { onChangeText(it) })
                Spacer(modifier = Modifier.padding(start = 8.dp))
                TextButton(onClick = {
                    onEditClick(Entry(text = text, timestamp = System.currentTimeMillis(), id = id))
                    dismiss()
                }) {
                    Text("Upravit")
                }

                TextButton(onClick = {
                    onDeleteClick()
                    dismiss()

                }) {
                    Text("Smazat")
                }
                DateTimePicker(initialDateTime = localDateTimeState, onDateTimeSelected = setDateTime)

            }
        },
        confirmButton = {
            TextButton(onClick = {
                dismiss() }) {
                Text("Storno")
            }
        }
    )
}
