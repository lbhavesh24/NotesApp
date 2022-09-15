package com.demo.noteapp

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.demo.noteapp.models.Notes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepo: NoteRepository,
    application: Application
) : AndroidViewModel(application) {
    val notes:MutableLiveData<List<Notes>> = MutableLiveData()

    fun getNotes() = viewModelScope.launch {
        noteRepo.fetchNotes().collect{ notes.value = it}
    }

    fun insertNote(notes: Notes) = viewModelScope.launch {
        noteRepo.insertNote(notes)
    }

    fun updateNote(notes: Notes) = viewModelScope.launch {
        noteRepo.updateNote(notes)
    }
}