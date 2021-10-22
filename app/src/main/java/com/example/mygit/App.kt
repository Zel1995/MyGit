package com.example.mygit

import android.app.Application
import com.github.terrakok.cicerone.Cicerone

class App: Application() {
    companion object{
        private val cicerone = Cicerone.create()
        val router get()=cicerone.router
        val navigatorHolder get() = cicerone.getNavigatorHolder()
    }
}