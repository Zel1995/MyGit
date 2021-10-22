package com.example.mygit.ui.users

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygit.App
import com.example.mygit.R
import com.example.mygit.data.MockRepositoryImpl
import com.example.mygit.databinding.FragmentUsersBinding
import com.example.mygit.domain.model.GitUser
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), UsersContract.View {
    private var viewBinding: FragmentUsersBinding? = null
    private val presenter by moxyPresenter { UsersPresenter(MockRepositoryImpl(), App.router) }
    private val adapter = UsersAdapter {
        presenter.onUser(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentUsersBinding.bind(view)
        initRv()
    }

    private fun initRv() {
        viewBinding?.rvUsers?.adapter = adapter
        viewBinding?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun setState(state: UsersContract.ViewBehavior) {

    }

    override fun setUsers(list: List<GitUser>) {
        adapter.setUsers(list)
    }
}