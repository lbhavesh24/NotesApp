package com.demo.noteapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.demo.noteapp.models.Notes
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface NotesDao {
    @Insert(onConflict = REPLACE)
    suspend fun addNote(note:Notes)

    @Query("SELECT * FROM notes_table ORDER BY updatedOn DESC")
    fun getAllNotes():Flow<List<Notes>>

    @Update
    suspend fun update (note:Notes)
}