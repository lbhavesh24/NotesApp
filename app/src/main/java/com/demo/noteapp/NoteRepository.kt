package com.demo.noteapp

import com.demo.noteapp.models.Notes
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityRetainedScoped
class NoteRepository @Inject constructor(
    private val notesDao: NotesDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    fun fetchNotes(): Flow<List<Notes>> {
        return notesDao.getAllNotes()
    }

    suspend fun insertNote(notes: Notes) {
        withContext(ioDispatcher) {
            notesDao.addNote(notes)
        }
    }

    suspend fun updateNote(notes: Notes) {
        withContext(ioDispatcher) {
            notesDao.update(notes)
        }
    }
}