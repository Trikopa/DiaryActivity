package com.example.diaryactivity.di

import android.content.Context
import androidx.room.Room
import com.example.diaryactivity.data.entries.EntryDatabase
import com.example.diaryactivity.data.listofentries.EntryListDao
import com.example.diaryactivity.data.listofentries.EntryListDatabase
import com.example.diaryactivity.data.listofentries.EntryListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.diaryactivity.data.listofentries.EntryRepoImpl


@Module
@InstallIn(SingletonComponent::class)
class EntryListModule {
    @Provides
    fun provideBookDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        EntryListDatabase::class.java,
        "entry_list_database"
    ).build()

    @Provides
    fun provideBookDao(
        bookDb: EntryListDatabase
    ) = bookDb.entryListDao()

    @Provides
    fun provideBookRepository(
        bookDao: EntryListDao
    ): EntryListRepository = EntryRepoImpl(
        dao = bookDao

    )
}