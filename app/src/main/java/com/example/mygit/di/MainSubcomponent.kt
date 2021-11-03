package com.example.mygit.di

import com.example.mygit.di.modules.PresenterModule
import com.example.mygit.di.modules.RouterModule
import com.example.mygit.ui.MainActivity
import com.example.mygit.ui.details.ReposFragment
import com.example.mygit.ui.users.UsersFragment
import dagger.Subcomponent
import javax.inject.Singleton

@Subcomponent(modules = [PresenterModule::class])
interface MainSubcomponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainSubcomponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: UsersFragment)
    fun inject(reposFragment: ReposFragment)

}