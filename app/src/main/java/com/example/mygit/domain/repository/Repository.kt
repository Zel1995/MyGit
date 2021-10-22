package com.example.mygit.domain.repository

import com.example.mygit.domain.model.GitUser
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface Repository {
    fun getUsers(): Single<List<GitUser>>
    fun getUser(id:String): Maybe<GitUser>
    fun addUser(user: GitUser)
}