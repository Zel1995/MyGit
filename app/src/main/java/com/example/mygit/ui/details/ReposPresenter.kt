package com.example.mygit.ui.details

import com.example.mygit.data.MockRepositoryImpl
import com.example.mygit.domain.model.GitHubRepository
import com.example.mygit.ui.users.Screen
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class ReposPresenter(private val repository: MockRepositoryImpl, private val router: Router) :
    UserContract.Presenter() {
    private val compositeDisposable = CompositeDisposable()
    override fun onRepository(gitHubRepository: GitHubRepository) {
        router.navigateTo(Screen.repoDetails(gitHubRepository))
    }

    override fun init(name: String) {
        viewState.setState(Loading(true))
        compositeDisposable += repository.getRepos(name)
            .subscribeOn(Schedulers.io())
            .map {
                it.map { reposRequest ->
                    GitHubRepository(
                        reposRequest.id,
                        reposRequest.name,
                        reposRequest.fullName,
                        reposRequest.description,
                        reposRequest.visibility,
                        reposRequest.forks,
                        reposRequest.watchers,
                        reposRequest.defaultBranch
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