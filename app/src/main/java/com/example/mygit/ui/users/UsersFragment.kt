package com.example.mygit.ui.users

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygit.di.App
import com.example.mygit.R
import com.example.mygit.data.RepositoryImpl
import com.example.mygit.data.network.RetrofitBuilder
import com.example.mygit.data.storage.GitRepositoriesCache
import com.example.mygit.data.storage.GitUsersCache
import com.example.mygit.databinding.FragmentUsersBinding
import com.example.mygit.domain.model.GitUser
import com.example.mygit.ui.MainActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), UsersContract.View {
    private var viewBinding: FragmentUsersBinding? = null
    @Inject
    lateinit var usersPresenter: UsersPresenter
    private val presenter by moxyPresenter {
        usersPresenter
    }
    private val compositeDisposable = CompositeDisposable()
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
        hideViews()
        when (state) {
            UsersContract.ViewBehavior.IDLE -> {
                viewBinding?.rvUsers?.visibility = View.VISIBLE
            }
            UsersContract.ViewBehavior.SUCCESS -> {
                viewBinding?.rvUsers?.visibility = View.VISIBLE
                Toast.makeText(context, getString(R.string.success), Toast.LENGTH_SHORT).show()
            }
            UsersContract.ViewBehavior.ERROR -> {
                viewBinding?.rvUsers?.visibility = View.VISIBLE
                Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
            }
            UsersContract.ViewBehavior.LOADING -> {
                viewBinding?.progressBar?.visibility = View.VISIBLE
            }
        }
    }

    private fun hideViews() {
        viewBinding?.rvUsers?.visibility = View.GONE
        viewBinding?.progressBar?.visibility = View.GONE
    }

    override fun setUsers(list: List<GitUser>) {
        adapter.setUsers(list)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as? MainActivity)?.mainSubcomponent?.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}