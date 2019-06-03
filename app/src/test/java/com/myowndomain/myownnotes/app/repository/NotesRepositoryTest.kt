package com.myowndomain.myownnotes.app.repository

import android.content.Context
import androidx.lifecycle.LiveData
import TestUtils.NOTE_1
import android.app.Application
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NotesRepositoryTest {

    @Mock
    private lateinit var mockContext: Context
    @Mock
    private lateinit var notesDAO: NotesDAO
    @Mock
    private lateinit var notes: LiveData<List<Note>>

    private lateinit var notesRepository: NotesRepository

    @Before
    fun init() {
        notesRepository = NotesRepository(mockContext as Application)
    }

    @Test
    fun getAllNotes_ReturnsAllNotes(){
        notesRepository.insert(NOTE_1)
        notesRepository.getAllNotes()

    }
}