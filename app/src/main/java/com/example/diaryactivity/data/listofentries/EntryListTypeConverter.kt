package com.example.diaryactivity.data.listofentries

import androidx.room.TypeConverter
import com.example.diaryactivity.model.entry.Entry
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class EntryListTypeConverter {
    @TypeConverter
    fun fromEntryList(entries: List<List<Entry>>): String {
        return Json.encodeToString(entries)
    }

    @TypeConverter
    fun toEntryList(entriesJson: String): List<List<Entry>> {
        return Json.decodeFromString(entriesJson)
    }
}
