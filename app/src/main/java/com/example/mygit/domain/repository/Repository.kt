package com.example.mygit.domain.repository

import com.example.mygit.data.bus.EventBus
import com.example.mygit.data.model.ReposRequest
import com.example.mygit.data.model.UsersRequest
import com.example.mygit.data.storage.entities.GitHubRepositoryEntity
import com.example.mygit.data.storage.entities.GitUserEntity
import com.example.mygit.domain.model.GitUser
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface Repository {
    fun getUsers(): Observable<List<GitUserEntity>>
    fun getRepos(login: String): Observable<List<GitHubRepositoryEntity>>
}