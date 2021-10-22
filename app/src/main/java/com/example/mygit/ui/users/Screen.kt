package com.example.mygit.ui.users

import com.example.mygit.domain.model.GitUser
import com.example.mygit.ui.details.UserFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screen {
    fun details(user: GitUser) = FragmentScreen { UserFragment.newInstance(user) }
    fun users() = FragmentScreen { UsersFragment() }
}