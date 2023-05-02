package com.example.diaryactivity.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.diaryactivity.model.entry.Entry
import com.example.diaryactivity.model.entry.EntryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: EntryViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    var text by rememberSaveable {
        mutableStateOf("")
    }
    val onChangeText = {it: String -> text = it}
    val scope = rememberCoroutineScope()
    val insert = {
        scope.launch {
            withContext(Dispatchers.IO) {
                viewModel.insertEntry(Entry(text = text, timestamp = System.currentTimeMillis()))
                text = ""
            }
        }
    }
    val delete = { entry: Entry ->
        scope.launch {
            withContext(Dispatchers.IO) {
                viewModel.deleteEntry(entry = entry)
            }
        }
    }
    val update = { entry: Entry ->
        scope.launch {
            withContext(Dispatchers.IO) {
                viewModel.updateEntry(entry = entry)
            }
        }
    }

    Scaffold(floatingActionButton = {
        ActionButton {
        insert()
    }
    }) {
        CombinedPage(
            entryList = uiState.entry,
            paddingValues = it,
            text = text,
            onChangeText = onChangeText,
            onDeleteClick = { that -> delete(that) },
            onEdit = { that -> update(that)},
            addEntry = { insert() }
            )

    }
}

