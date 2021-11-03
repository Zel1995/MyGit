package com.example.mygit.di.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RouterModule {
    @Singleton
    @Provides
    fun providesCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    fun providesRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    fun providesNavigator(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.getNavigatorHolder()
}