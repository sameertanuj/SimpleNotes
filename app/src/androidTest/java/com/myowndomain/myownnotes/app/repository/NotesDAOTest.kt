package com.myowndomain.myownnotes.app.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import TestUtils.NOTE_1
import TestUtils.NOTE_2
import TestUtils.NOTE_3
import get
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException
import org.junit.Rule




@RunWith(AndroidJUnit4::class)
open class NotesDAOTest {

    private lateinit var notesDAO: NotesDAO
    private lateinit var notesDataBase: NotesDataBase
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        notesDataBase = Room.inMemoryDatabaseBuilder(
            context, NotesDataBase::class.java).build()
        notesDAO = notesDataBase.notesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        notesDataBase.close()
    }

    @Test
    fun insert_NoConflicts_InsertsNotes() {
        notesDAO.insert(NOTE_1)
        val allNotes = notesDAO.getAllNotes().get()
        Assert.assertEquals(NOTE_1, allNotes[0])
    }

    @Test
    fun delete_DeletesNote() {
        notesDAO.insert(NOTE_1)
        notesDAO.insert(NOTE_2)
        notesDAO.insert(NOTE_3)
        notesDAO.delete(NOTE_1)
        val allNotes = notesDAO.getAllNotes().get()
        Assert.assertEquals(listOf(NOTE_2, NOTE_3), allNotes)
    }

    @Test
    fun getAllNotes_RetrievesAllNote() {
        notesDAO.insert(NOTE_1)
        notesDAO.insert(NOTE_2)
        notesDAO.insert(NOTE_3)
        val allNotes = notesDAO.getAllNotes().get()
        Assert.assertEquals(listOf(NOTE_1, NOTE_2, NOTE_3), allNotes)
    }

}