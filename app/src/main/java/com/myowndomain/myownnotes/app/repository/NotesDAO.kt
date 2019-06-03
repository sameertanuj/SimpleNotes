package com.myowndomain.myownnotes.app.repository

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Query("SELECT * from notes ORDER BY title ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Delete
    fun delete(note: Note)

}