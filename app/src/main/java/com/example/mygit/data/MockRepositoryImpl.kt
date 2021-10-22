package com.example.mygit.data

import com.example.mygit.domain.model.GitUser
import com.example.mygit.domain.repository.Repository
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class MockRepositoryImpl : Repository {
    private val users = mutableListOf<GitUser>(
        GitUser("1", "Иван", 19),
        GitUser("2", "Кристина", 20),
        GitUser("3", "Сергей", 21),
        GitUser("4", "Дмитрий", 22),
        GitUser("5", "Владимир", 23),
        GitUser("6", "Александра", 24),
        GitUser("7", "Алексей", 25),
        GitUser("8", "Кирилл", 26),
    )

    override fun getUsers(): Single<List<GitUser>> {
        return Single.just(users)
    }

    override fun getUser(id: String): Maybe<GitUser> {
        users.forEach{if(it.id == id){
            return Maybe.just(it)
        } }
        return Maybe.empty()
    }

    override fun addUser(user: GitUser) {
        users.add(user)
    }

}