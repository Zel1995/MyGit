package com.example.mygit.di.modules

import com.example.mygit.data.RepositoryImpl
import com.example.mygit.data.network.GithubApi
import com.example.mygit.data.storage.GitRepositoriesCache
import com.example.mygit.data.storage.GitUsersCache
import com.example.mygit.domain.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesRepository(
        githubApi: GithubApi,
        cacheUser: GitUsersCache,
        cacheRepo: GitRepositoriesCache
    ): Repository = RepositoryImpl(
        githubApi,
        cacheUser,
        cacheRepo
    )
}