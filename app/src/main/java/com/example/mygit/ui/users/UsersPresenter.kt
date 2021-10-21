package com.example.mygit.ui.users

import com.example.mygit.domain.model.GitUser
import com.example.mygit.domain.repository.Repository
import com.github.terrakok.cicerone.Router

class UsersPresenter(
    private val repository: Repository, private val router: Router
) : UsersContract.Presenter() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setUsers(repository.getUsers())
    }

    override fun onUser(gitUser: GitUser) {

    }
}