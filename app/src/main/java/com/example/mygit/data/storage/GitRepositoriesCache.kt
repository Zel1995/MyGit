package com.example.mygit.data.storage

import com.example.mygit.data.storage.entities.GitHubRepositoryEntity
import io.reactivex.Single

class GitRepositoriesCache(private val dao: GitHubDao) {
    fun getRepos(login: String): Single<List<GitHubRepositoryEntity>> {
        return dao.getGitRepositoriesByLogin(login)
    }

    fun retain(
        login: String,
        repositories: List<GitHubRepositoryEntity>
    ): Single<List<GitHubRepositoryEntity>> {
        return dao.addGitRepositories(repositories)
            .andThen(getRepos(login))


    }
}