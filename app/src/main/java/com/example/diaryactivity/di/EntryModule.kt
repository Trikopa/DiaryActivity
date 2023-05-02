package com.example.diaryactivity.di

import android.content.Context
import androidx.room.Room
import com.example.diaryactivity.data.entries.EntryDao
import com.example.diaryactivity.data.entries.EntryDatabase
import com.example.diaryactivity.data.entries.EntryRepository
import com.example.diaryactivity.data.entries.EntryRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class EntryModule {
    @Provides
    fun provideBookDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        EntryDatabase::class.java,
        "diary_database"
    ).build()

    @Provides
    fun provideBookDao(
        bookDb: EntryDatabase
    ) = bookDb.entryDao()

    @Provides
    fun provideBookRepository(
        bookDao: EntryDao
    ): EntryRepository = EntryRepoImpl(
        dao = bookDao

    )
}