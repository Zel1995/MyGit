package com.example.mygit.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mygit.data.storage.entities.GitHubRepositoryEntity
import com.example.mygit.data.storage.entities.GitUserEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface GitHubDao {
    @Query("SELECT * FROM GitUserEntity")
    fun getGitUsers(): Single<List<GitUserEntity>>

    @Query("SELECT * FROM GitHubRepositoryEntity WHERE owner_login = :login")
    fun getGitRepositoriesByLogin(login:String): Single<List<GitHubRepositoryEntity>>

    @Query("DELETE FROM GitUserEntity")
    fun deleteAllFromGitUserEntity()

    @Query("DELETE FROM GitHubRepositoryEntity")
    fun deleteAllFromGitHubRepository()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGitUsers(gitUsersEntity: List<GitUserEntity>):Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGitRepositories(gitHubRepositoriesEntity: List<GitHubRepositoryEntity>):Completable

}