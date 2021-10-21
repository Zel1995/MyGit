package com.example.mygit.domain.repository

import com.example.mygit.domain.model.GitUser

interface Repository {
    fun getUsers(): List<GitUser>
    fun getUser(id:String): GitUser?
    fun addUser(user: GitUser)
}