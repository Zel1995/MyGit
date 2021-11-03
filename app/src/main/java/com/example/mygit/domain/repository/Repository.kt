package com.example.mygit.domain.repository

import com.example.mygit.data.storage.entities.GitHubRepositoryEntity
import com.example.mygit.data.storage.entities.GitUserEntity
import io.reactivex.Observable

interface Repository {
    fun getUsers(): Observable<List<GitUserEntity>>
    fun getRepos(login: String): Observable<List<GitHubRepositoryEntity>>
}