package com.example.diaryactivity.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.diaryactivity.model.entry.Entry

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CombinedPage(
    entryList: List<Entry>,
    paddingValues: PaddingValues,
    text: String,
    onChangeText: (String) -> Unit,
    onDeleteClick: (Entry) -> Unit,
    onEdit: (Entry) -> Unit = {},
    addEntry: () -> Unit = {}
)
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = text,
            onValueChange = { onChangeText(it)},
            keyboardActions = KeyboardActions(
                onDone = { addEntry() }

            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done)
        )
        Spacer(modifier = Modifier.padding(bottom = 32.dp))
        EntryList(
            entryList = entryList,
            onDelete = { that: Entry -> onDeleteClick(that) },
            onEdit =  { that: Entry -> onEdit(that) },
            )
        Spacer(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun ActionButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}

