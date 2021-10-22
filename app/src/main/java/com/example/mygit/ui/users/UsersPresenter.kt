package com.example.mygit.ui.users

import com.example.mygit.domain.model.GitUser
import com.example.mygit.domain.repository.Repository
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class UsersPresenter(
    private val repository: Repository, private val router: Router
) : UsersContract.Presenter() {

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        compositeDisposable += repository.getUsers()
            .subscribe({
                viewState.setUsers(it)
            }, { viewState.setState(UsersContract.ViewBehavior.ERROR) })
    }

    override fun onUser(gitUser: GitUser) {
        router.navigateTo(Screen.details(gitUser))
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}