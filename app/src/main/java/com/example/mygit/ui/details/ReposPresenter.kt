package com.example.mygit.ui.details

import com.example.mygit.data.RepositoryImpl
import com.example.mygit.domain.model.GitHubRepository
import com.example.mygit.ui.users.Screen
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class ReposPresenter(private val repository: RepositoryImpl, private val router: Router) :
    UserContract.Presenter() {
    private val compositeDisposable = CompositeDisposable()
    override fun onRepository(gitHubRepository: GitHubRepository) {
        router.navigateTo(Screen.repoDetails(gitHubRepository))
    }

    override fun init(name: String) {
        viewState.setState(Loading(true))
        compositeDisposable += repository
            .getRepos(name)
            .subscribeOn(Schedulers.io())
            .map {
                it.map { reposEntity ->
                    GitHubRepository(
                        reposEntity.id,
                        reposEntity.name,
                        reposEntity.fullName,
                        reposEntity.description,
                        reposEntity.visibility,
                        reposEntity.forks,
                        reposEntity.watchers,
                        reposEntity.defaultBranch,
                        reposEntity.ownerLogin
                    )
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { viewState.setState(Success(it)) },
                { viewState.setState(Error(it)) }
            )
        viewState.setState(Loading(false))
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}