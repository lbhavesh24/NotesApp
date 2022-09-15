package com.demo.noteapp

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase):NotesDao{
        return noteDatabase.noteDao()
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext appContext:Context):
            NoteDatabase{
        return Room.databaseBuilder(
            appContext,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }
}