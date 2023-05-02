package com.example.diaryactivity.data.listofentries

import com.example.diaryactivity.model.entrylist.EntryList
import kotlinx.coroutines.flow.Flow

interface EntryListRepository {

    suspend fun getAllItemsStream(): Flow<List<EntryList>>

    suspend fun insertItem(item: EntryList)

    suspend fun deleteItem(item: EntryList)

    suspend fun updateItem(item: EntryList)
}