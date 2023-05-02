package com.example.diaryactivity.data.listofentries

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.diaryactivity.model.entrylist.EntryList

@Database(entities = [EntryList::class], version = 1, exportSchema = false)
abstract class EntryListDatabase:RoomDatabase() {
    abstract fun entryListDao(): EntryListDao
}