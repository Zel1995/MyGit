package com.example.mygit.domain.repository

import com.example.mygit.data.bus.EventBus
import com.example.mygit.domain.model.GitUser
import io.reactivex.Maybe
import io.reactivex.Observable

interface Repository {
    fun getUsers(): Observable<MutableList<GitUser>>
    fun getUser(id: String): Maybe<GitUser>
    fun addUser(user: GitUser)
    fun setLike(like:EventBus.Like)
}