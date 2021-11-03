package com.example.mygit.di.modules

import com.example.mygit.domain.repository.Repository
import com.example.mygit.ui.details.ReposPresenter
import com.example.mygit.ui.users.UsersPresenter
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {
    @Provides
    fun providesUsersPresenter(repository: Repository, router: Router): UsersPresenter {
        return UsersPresenter(repository, router)
    }

    @Provides
    fun providerRepositoriesPresenter(repository: Repository, router: Router):ReposPresenter{
        return ReposPresenter(repository,router)
    }
}