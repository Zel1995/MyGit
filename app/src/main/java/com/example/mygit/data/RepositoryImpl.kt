package com.example.mygit.data

import com.example.mygit.data.network.GithubApi
import com.example.mygit.data.storage.GitRepositoriesCache
import com.example.mygit.data.storage.GitUsersCache
import com.example.mygit.data.storage.entities.GitHubRepositoryEntity
import com.example.mygit.data.storage.entities.GitUserEntity
import com.example.mygit.domain.repository.Repository
import io.reactivex.Observable

class RepositoryImpl(
    private val api: GithubApi,
    private val cacheUser: GitUsersCache,
    private val cacheRepos: GitRepositoriesCache
) : Repository {

    override fun getUsers(): Observable<List<GitUserEntity>> {
        return Observable.concat(
            cacheUser.getUsers().toObservable(),
            api.getUsers()
                .flatMap {
                    cacheUser.retain(it.map {
                        GitUserEntity(
                            it.id,
                            it.login,
                            it.avatarUrl,
                            it.url,
                            it.reposUrl
                        )
                    })
                }.toObservable()
        )
    }

    override fun getRepos(login: String): Observable<List<GitHubRepositoryEntity>> {
        return Observable.concat(
            cacheRepos.getRepos(login).toObservable(),
            api.getUserReposByLogin(login)
                .flatMap {
                    cacheRepos.retain(login, it.map {
                        GitHubRepositoryEntity(
                            it.id,
                            it.name,
                            it.fullName,
                            it.description,
                            it.visibility,
                            it.forks,
                            it.watchers,
                            it.defaultBranch,
                            it.owner.login
                        )
                    })
                }.toObservable()
        )
    }
}
