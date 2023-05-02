package com.example.diaryactivity.data.entries

import com.example.diaryactivity.model.entry.Entry
import kotlinx.coroutines.flow.Flow

interface EntryRepository {

    suspend fun getAllItemsStream(): Flow<List<Entry>>

    suspend fun insertItem(item: Entry)

    suspend fun deleteItem(item: Entry)

    suspend fun updateItem(item: Entry)
}