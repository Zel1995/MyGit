package com.example.mygit.data

import com.example.mygit.domain.model.GitUser
import com.example.mygit.domain.repository.Repository
import java.util.*

class MockRepositoryImpl : Repository {
    private val users = mutableListOf<GitUser>(
        GitUser(UUID.randomUUID().toString(), "Иван", 19),
        GitUser(UUID.randomUUID().toString(), "Кристина", 20),
        GitUser(UUID.randomUUID().toString(), "Сергей", 21),
        GitUser(UUID.randomUUID().toString(), "Дмитрий", 22),
        GitUser(UUID.randomUUID().toString(), "Владимир", 23),
        GitUser(UUID.randomUUID().toString(), "Александра", 24),
        GitUser(UUID.randomUUID().toString(), "Алексей", 25),
        GitUser(UUID.randomUUID().toString(), "Кирилл", 26),
    )

    override fun getUsers(): List<GitUser> {
        return users
    }

    override fun getUser(id: String): GitUser? {
        users.forEach{if(it.id == id){
            return it
        } }
        return null
    }

    override fun addUser(user: GitUser) {
        users.add(user)
    }

}