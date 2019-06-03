package com.samoana.donotdisturb.di

import android.app.Application
import com.myowndomain.myownnotes.app.NotesApplication
import com.myowndomain.myownnotes.app.di.AppModule
import com.myowndomain.myownnotes.app.di.FragmentsBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        FragmentsBindingModule::class
    ]
)
interface AppComponent: AndroidInjector<NotesApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application):Builder
        fun build(): AppComponent
    }
}