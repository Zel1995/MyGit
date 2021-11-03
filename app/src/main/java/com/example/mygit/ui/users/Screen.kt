package com.example.mygit.ui.users

import com.example.mygit.domain.model.GitHubRepository
import com.example.mygit.domain.model.GitUser
import com.example.mygit.ui.details.ReposFragment
import com.example.mygit.ui.repo.RepoFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screen {
    fun details(user: GitUser) = FragmentScreen { ReposFragment.newInstance(user) }
    fun repos() = FragmentScreen { UsersFragment() }
    fun repoDetails(gitHubRepository: GitHubRepository) =
        FragmentScreen { RepoFragment.newInstance(gitHubRepository) }
}