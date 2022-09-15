package com.demo.noteapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.noteapp.models.Notes

@Database(entities = [Notes::class], version = 1)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NotesDao

    companion object{
        const val DATABASE_NAME="NoteDatabase"
    }
}