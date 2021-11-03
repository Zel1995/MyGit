package com.example.mygit.ui.details

import com.example.mygit.domain.model.GitHubRepository
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

abstract class UserContract {
    interface View : MvpView {
        @Skip
        fun setState(viewState: ViewState<List<GitHubRepository>>)
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun onRepository(gitHubRepository: GitHubRepository)
        abstract fun init(name: String)
    }
}

sealed class ViewState<T>
class Success<T>(val value: T) : ViewState<T>()
class Error<T>(val error: Throwable) : ViewState<T>()
class Loading<T>(val isLoading: Boolean) : ViewState<T>()