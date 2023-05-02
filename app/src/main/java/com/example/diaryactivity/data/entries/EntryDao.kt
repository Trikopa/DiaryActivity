package com.example.diaryactivity.data.entries

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.diaryactivity.model.entry.Entry
import kotlinx.coroutines.flow.Flow

@Dao

interface EntryDao {

    @Query("SELECT * FROM Entry ORDER BY id DESC")
    fun allEntries(): Flow<List<Entry>>

    @Insert
    suspend fun newEntry(entry: Entry)

    @Delete
    suspend fun deleteEntry(entry: Entry)

    @Update
    suspend fun editEntry(entry: Entry)
}