package com.myowndomain.myownnotes.app.di

import android.app.Application
import androidx.room.Room
import com.myowndomain.myownnotes.app.repository.NotesDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Provides
    @Singleton
    fun provideNotesDataBase(app: Application) =
        Room.databaseBuilder(app, NotesDataBase::class.java, "notes_database").build()
    @Provides
    @Singleton
    fun provideNotesDAO(eventDatabase: NotesDataBase) =
        eventDatabase.notesDao()
}