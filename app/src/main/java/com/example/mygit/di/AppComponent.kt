package com.example.mygit.di

import com.example.mygit.di.modules.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    DataBaseModule::class,
    RouterModule::class])
interface AppComponent {
    fun mainSubcomponent(): MainSubcomponent.Factory
}