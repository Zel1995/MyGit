package com.example.mygit.data.network

import com.example.mygit.data.model.ReposRequest
import com.example.mygit.data.model.UsersRequest
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/users")
    fun getUsers(): Single<List<UsersRequest>>

    @GET("/users/{username}/repos")
    fun getUserReposByLogin(@Path("username")name:String):Single<List<ReposRequest>>
}