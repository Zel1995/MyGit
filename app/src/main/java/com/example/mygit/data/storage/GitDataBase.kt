package com.example.mygit.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mygit.data.storage.entities.GitHubRepositoryEntity
import com.example.mygit.data.storage.entities.GitUserEntity

@Database(entities = [GitHubRepositoryEntity::class, GitUserEntity::class], version = 1)
abstract class GitDataBase : RoomDatabase() {
    abstract fun gitHubDao():GitHubDao
}