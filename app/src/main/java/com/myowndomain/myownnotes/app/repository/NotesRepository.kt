package com.myowndomain.myownnotes.app.repository

import android.os.AsyncTask
import android.util.Log
import javax.inject.Inject

class NotesRepository @Inject constructor(private val notesDAO: NotesDAO) {

    fun getAllNotes() = notesDAO.getAllNotes()

    fun insert(note: Note) {
        InsertAsyncTask(notesDAO).execute(note)
    }

    fun delete(note: Note) {
        DeleteAsyncTask(notesDAO).execute(note)
    }

    private class InsertAsyncTask (private val notesDAO: NotesDAO) : AsyncTask<Note, Void, String>() {
        override fun doInBackground(vararg params: Note): String {
            notesDAO.insert(params[0])
            return "Success"
        }

        override fun onPostExecute(result: String) {
            Log.i("", "Insertion Success")
        }
    }

    private class DeleteAsyncTask (private val notesDAO: NotesDAO) : AsyncTask<Note, Void, String>() {
        override fun doInBackground(vararg params: Note): String {
            notesDAO.delete(params[0])
            return "Success"
        }

        override fun onPostExecute(result: String) {
            Log.i("", "Deletion  Success")
        }
    }
}