package com.example.diaryactivity.model.entrylist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diaryactivity.model.entry.Entry
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Entity(tableName = "EntryList")
data class EntryList(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "entries")
    val entriesJson: String
) {
    val entries: List<List<Entry>>
        get() = Json.decodeFromString(entriesJson)
}
