package com.example.mygit.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mygit.App
import com.example.mygit.R
import com.example.mygit.data.MockRepositoryImpl
import com.example.mygit.data.network.RetrofitBuilder
import com.example.mygit.databinding.FragmentReposBinding
import com.example.mygit.domain.model.GitHubRepository
import com.example.mygit.domain.model.GitUser
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ReposFragment : MvpAppCompatFragment(R.layout.fragment_repos), UserContract.View {
    companion object {
        private const val USER_KEY = "USER_KEY"
        fun newInstance(user: GitUser) =
            ReposFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER_KEY, user)
                }
            }
    }

    private val adapter = ReposAdapter {
        presenter.onRepository(it)
    }
    private val presenter by moxyPresenter {
        ReposPresenter(
            MockRepositoryImpl(RetrofitBuilder.create()),
            App.router
        )
    }
    private val compositeDisposable = CompositeDisposable()
    private var viewBinding: FragmentReposBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentReposBinding.bind(view)
        initViews()
    }


    private fun initViews() {
        val user = arguments?.getParcelable<GitUser>(USER_KEY) ?: return
        presenter.init(user.login)
        viewBinding?.reposRecyclerView?.adapter = adapter
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun setState(viewState: ViewState<List<GitHubRepository>>) {
        when (viewState) {
            is Success -> {
                adapter.setData(viewState.value)
            }
            is Error -> {
                Toast.makeText(requireContext(), viewState.error.message, Toast.LENGTH_SHORT).show()
            }
            is Loading -> {
                viewBinding?.progressBar?.visibility =
                    if (viewState.isLoading) View.VISIBLE else View.GONE
                viewBinding?.reposRecyclerView?.visibility =
                    if (viewState.isLoading) View.GONE else View.VISIBLE
            }
        }
    }
}