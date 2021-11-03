package com.example.mygit.ui.details

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mygit.R
import com.example.mygit.data.storage.GitRepositoriesCache
import com.example.mygit.databinding.FragmentReposBinding
import com.example.mygit.di.App
import com.example.mygit.domain.model.GitHubRepository
import com.example.mygit.domain.model.GitUser
import com.example.mygit.domain.repository.Repository
import com.example.mygit.ui.MainActivity
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

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

    @Inject
    lateinit var reposPresenter: ReposPresenter

    private val adapter = ReposAdapter {
        presenter.onRepository(it)
    }
    private val presenter by moxyPresenter {
        reposPresenter
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as? MainActivity)?.mainSubcomponent?.inject(this)
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