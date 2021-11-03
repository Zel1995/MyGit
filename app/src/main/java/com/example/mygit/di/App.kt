package com.example.mygit.di

import android.app.Application
import com.example.mygit.data.storage.GitHubDao
import com.example.mygit.di.modules.ApplicationModule
import com.github.terrakok.cicerone.Cicerone

class App : Application() {
    companion object {
        private val cicerone = Cicerone.create()
        val router get() = cicerone.router
        val navigatorHolder get() = cicerone.getNavigatorHolder()
        private var dao: GitHubDao? = null
        fun getDao() = dao ?: throw RuntimeException("db has not been created")
    }

    val appComponent = DaggerAppComponent.builder()
        .applicationModule(ApplicationModule(this))
        .build()

}