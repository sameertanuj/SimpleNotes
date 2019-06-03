package com.myowndomain.myownnotes.app.viewmodel

import androidx.lifecycle.ViewModel
import com.myowndomain.myownnotes.app.repository.Note
import com.myowndomain.myownnotes.app.repository.NotesRepository
import javax.inject.Inject

class NotesViewModel @Inject constructor(private var notesRepository: NotesRepository) : ViewModel() {

    fun getAllNotes() = notesRepository.getAllNotes()

    fun insert(note: Note) {
        notesRepository.insert(note)
    }

    fun delete(note: Note) {
        notesRepository.delete(note)
    }
}
