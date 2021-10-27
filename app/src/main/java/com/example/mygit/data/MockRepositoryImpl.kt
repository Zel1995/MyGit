package com.example.mygit.data

import com.example.mygit.data.bus.EventBus
import com.example.mygit.data.model.ReposRequest
import com.example.mygit.data.model.UsersRequest
import com.example.mygit.data.network.GithubApi
import com.example.mygit.domain.repository.Repository
import io.reactivex.Single

class MockRepositoryImpl(private val api: GithubApi) : Repository {

    override fun getUsers(): Single<List<UsersRequest>> {
        return api.getUsers()
    }

    override fun getRepos(name: String): Single<List<ReposRequest>> {
        return api.getUserReposByName(name)
    }


}