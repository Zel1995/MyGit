package com.example.mygit

import android.app.Application
import androidx.room.Room
import com.example.mygit.data.bus.EventBus
import com.example.mygit.data.storage.GitDataBase
import com.example.mygit.data.storage.GitHubDao
import com.github.terrakok.cicerone.Cicerone
import java.lang.RuntimeException

class App : Application() {
    companion object {
        private val cicerone = Cicerone.create()
        val router get() = cicerone.router
        val navigatorHolder get() = cicerone.getNavigatorHolder()
        private var dao: GitHubDao? = null
        fun getDao() = dao ?: throw RuntimeException("db has not been created")
    }

    override fun onCreate() {
        super.onCreate()
        dao = Room.databaseBuilder(this, GitDataBase::class.java, "GitHubDataBase")
            .fallbackToDestructiveMigration()
            .build().gitHubDao()
    }

    val counterBus = EventBus()
}