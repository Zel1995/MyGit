package com.example.mygit.ui.users

import com.example.mygit.data.bus.EventBus
import com.example.mygit.domain.model.GitUser
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

abstract class UsersContract {
    enum class ViewBehavior {
        IDLE, LOADING, SUCCESS, ERROR
    }

    interface View : MvpView {
        @Skip
        fun setState(state: ViewBehavior)

        @AddToEndSingle
        fun setUsers(list: List<GitUser>)
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun onUser(gitUser: GitUser)
        abstract fun like(like: EventBus.Like)
    }
}