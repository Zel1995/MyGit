package com.example.mygit.data.storage

import com.example.mygit.data.storage.entities.GitUserEntity
import io.reactivex.Single
import javax.inject.Inject

class GitUsersCache @Inject constructor(private val dao: GitHubDao) {
    fun getUsers(): Single<List<GitUserEntity>> {
        return dao.getGitUsers()
    }

    fun retain(users: List<GitUserEntity>): Single<List<GitUserEntity>> {
        return dao.addGitUsers(users)
            .andThen(getUsers())
    }
}