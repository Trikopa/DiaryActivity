package com.example.diaryactivity.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.diaryactivity.model.entry.Entry

import com.example.diaryactivity.utils.formattedDate

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun EntryList(
    entryList: List<Entry>,
    onClick: (Entry) -> Unit = {},
    onLongClick: (Entry) -> Unit = {},
    onDelete: (Entry) -> Unit,
    onEdit: (Entry) -> Unit
) {
    LazyColumn(content = {
        items(entryList) { entry ->
            EntryCard(entry,
                onClick = { onClick(entry) },
                onLongClick = { onLongClick(entry) },
                onEdit = { that -> onEdit(that)  }
            ) { that: Entry -> onDelete(that) }
        }
    })
}





@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EntryCard
            (
    entry: Entry,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    onEdit: (Entry) -> Unit,
    onDelete: (Entry) -> Unit
) {

    var show by rememberSaveable {
        mutableStateOf(false)
    }
    var newText by rememberSaveable {
        mutableStateOf(entry.text)
    }


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
        Text(text = formattedDate(entry.timestamp))
    }
    ElevatedCard(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { onClick() }
            .combinedClickable(onLongClick = { show = true }) {}
        ,
        shape = ShapeDefaults.Medium,

        ) {

        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = entry.text)
        }
    }

    if(show) {
        CardPopup(
            onDeleteClick = { onDelete(entry) },
            dismiss = { show = false },
            onChangeText = { newText = it },
            text = newText,
            onEditClick = { that: Entry -> onEdit(that) },
            id = entry.id,
            simpleNotification =  {  })
    }
}

