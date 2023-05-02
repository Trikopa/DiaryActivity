package com.example.diaryactivity.model.entrylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryactivity.data.entries.EntryRepository
import com.example.diaryactivity.data.listofentries.EntryListRepository
import com.example.diaryactivity.model.entry.Entry
import com.example.diaryactivity.model.entry.EntryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class EntryListState(
    val entryLists: List<EntryList> = emptyList(),
    val newEntryList:List<Entry> = emptyList())

@HiltViewModel
class EntryListViewModel @Inject constructor (private val repository: EntryListRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(EntryListState())
    val uiState: StateFlow<EntryListState> = _uiState

    init {
        viewModelScope.launch {
            repository.getAllItemsStream().collect { entries ->
                _uiState.value = EntryListState(entryLists = entries)
            }
        }
    }

    fun insertEntryList(entry: EntryList) {
        // insert a new entry into the database
        viewModelScope.launch {
            repository.insertItem(entry)
        }
    }

    fun deleteEntryList(entry: EntryList) {
        // delete an entry from the database
        viewModelScope.launch {
            repository.deleteItem(entry)
        }
    }



    fun updateEntryList(entry: EntryList) {
        // update an entry in the database
        viewModelScope.launch {
            repository.updateItem(entry)
        }
    }

}