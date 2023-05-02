package com.example.diaryactivity.data.listofentries

import com.example.diaryactivity.model.entrylist.EntryList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EntryRepoImpl @Inject constructor(private val dao: EntryListDao): EntryListRepository{
    override suspend fun getAllItemsStream(): Flow<List<EntryList>> {
        return dao.getAllEntryLists()
    }

    override suspend fun insertItem(item: EntryList) {
        dao.addEntryList(item)
    }

    override suspend fun deleteItem(item: EntryList) {
        dao.deleteEntryList(item)
    }

    override suspend fun updateItem(item: EntryList) {
        dao.editEntryList(item)
    }
}