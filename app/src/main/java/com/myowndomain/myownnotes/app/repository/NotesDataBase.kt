package com.myowndomain.myownnotes.app.repository

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDataBase : RoomDatabase() {
    abstract fun notesDao(): NotesDAO

    companion object {
        @Volatile
        private var INSTANCE: NotesDataBase? = null
        @JvmStatic
        fun getDatabase(context: Context): NotesDataBase? {
            if (INSTANCE == null) {
                synchronized(NotesDataBase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            NotesDataBase::class.java,
                            "notes_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}