package com.example.diaryactivity.data.listofentries

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.diaryactivity.model.entrylist.EntryList
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryListDao {

    @Query("SELECT * FROM EntryList")
    fun getAllEntryLists(): Flow<List<EntryList>>

    @Insert
    suspend fun addEntryList(entryList: EntryList)


    @Delete
    suspend fun deleteEntryList(entry: EntryList)

    @Update
    suspend fun editEntryList(entry: EntryList)
}