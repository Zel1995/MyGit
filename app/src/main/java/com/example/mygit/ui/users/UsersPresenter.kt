package com.example.mygit.ui.users

import com.example.mygit.data.bus.EventBus
import com.example.mygit.domain.model.GitUser
import com.example.mygit.domain.repository.Repository
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class UsersPresenter(
    private val repository: Repository,
    private val router: Router,
) : UsersContract.Presenter() {

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setState(UsersContract.ViewBehavior.LOADING)
        compositeDisposable += repository.getUsers()
            .subscribeOn(Schedulers.io())
            .map { it.map{userRequest ->
                GitUser(
                    userRequest.id,
                    userRequest.login,
                    userRequest.avatarUrl,
                    userRequest.url,
                    userRequest.reposUrl
                )
            } }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.setUsers(it)
            }, { viewState.setState(UsersContract.ViewBehavior.ERROR) })
        viewState.setState(UsersContract.ViewBehavior.IDLE)
    }

    override fun onUser(gitUser: GitUser) {
        router.navigateTo(Screen.details(gitUser))
        viewState.setState(UsersContract.ViewBehavior.SUCCESS)
    }

    override fun like(like: EventBus.Like) {
        //repository.setLike(like)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}