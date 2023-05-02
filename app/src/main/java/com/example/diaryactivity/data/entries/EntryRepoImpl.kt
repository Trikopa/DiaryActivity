package com.example.diaryactivity.data.entries

import com.example.diaryactivity.model.entry.Entry
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EntryRepoImpl @Inject constructor(private val dao: EntryDao): EntryRepository {
    override suspend fun getAllItemsStream(): Flow<List<Entry>> {
        return dao.allEntries()
    }

    override suspend fun insertItem(item: Entry) {
        dao.newEntry(item)
    }

    override suspend fun deleteItem(item: Entry) {
        dao.deleteEntry(entry = item)
    }

    override suspend fun updateItem(item: Entry) {
        return dao.editEntry(entry = item)
    }
}