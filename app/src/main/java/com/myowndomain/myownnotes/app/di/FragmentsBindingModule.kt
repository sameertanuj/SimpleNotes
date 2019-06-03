package com.myowndomain.myownnotes.app.di


import com.myowndomain.myownnotes.app.ui.AddNotesFragment
import com.myowndomain.myownnotes.app.ui.NotesViewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBindingModule {
    @ContributesAndroidInjector
    abstract fun addNotesFragment():AddNotesFragment

    @ContributesAndroidInjector
    abstract fun notesViewFragment():NotesViewFragment
}