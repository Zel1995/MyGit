package com.example.mygit.di.modules

import android.app.Application
import androidx.room.Room
import com.example.mygit.data.storage.GitDataBase
import com.example.mygit.data.storage.GitHubDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun providesGitDataBase(application: Application): GitDataBase {
        return Room.databaseBuilder(application, GitDataBase::class.java, "GitHubDb")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesGitHubDao(gitDataBase: GitDataBase): GitHubDao {
        return gitDataBase.gitHubDao()

    }
}