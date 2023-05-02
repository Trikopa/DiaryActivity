package com.example.diaryactivity.model.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryactivity.data.entries.EntryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class EntryState(
    val entry: List<Entry> = emptyList(),
    val newEntryText: String = "",
    val newEntryTimestamp: String = "")


@HiltViewModel
class EntryViewModel @Inject constructor (private val repository: EntryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(EntryState())
    val uiState: StateFlow<EntryState> = _uiState

    init {
        viewModelScope.launch {
            repository.getAllItemsStream().collect { entries ->
                _uiState.value = EntryState(entry = entries)
            }
        }
    }

    fun insertEntry(entry: Entry) {
        // insert a new entry into the database
        viewModelScope.launch {
            repository.insertItem(entry)
        }
    }

    fun deleteEntry(entry: Entry) {
        // delete an entry from the database
        viewModelScope.launch {
            repository.deleteItem(entry)
        }
    }



    fun updateEntry(entry: Entry) {
        // update an entry in the database
        viewModelScope.launch {
            repository.updateItem(entry)
        }
    }

}

