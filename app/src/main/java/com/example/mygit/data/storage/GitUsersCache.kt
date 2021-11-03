package com.example.mygit.data.storage

import com.example.mygit.data.storage.entities.GitUserEntity
import io.reactivex.Single

class GitUsersCache(private val dao: GitHubDao) {
    fun getUsers(): Single<List<GitUserEntity>> {
        return dao.getGitUsers()
    }

    fun retain(users: List<GitUserEntity>): Single<List<GitUserEntity>> {
        return dao.addGitUsers(users)
            .andThen(getUsers())
    }
}