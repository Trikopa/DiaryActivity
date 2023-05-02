package com.example.diaryactivity.model.entry

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String,
    val timestamp: Long
)

