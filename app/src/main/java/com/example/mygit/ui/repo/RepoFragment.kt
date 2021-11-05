package com.example.mygit.ui.repo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mygit.R
import com.example.mygit.databinding.FragmentRepoBinding
import com.example.mygit.domain.model.GitHubRepository

class RepoFragment : Fragment(R.layout.fragment_repo) {
    companion object {
        private const val REPO_KEY = "REPO_FRAGMENT_KEY"
        fun newInstance(gitHubRepository: GitHubRepository) =
            RepoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(REPO_KEY, gitHubRepository)
                }
            }
    }
    private var viewBinding :FragmentRepoBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentRepoBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        val repo = arguments?.getParcelable<GitHubRepository>(REPO_KEY) ?: return
        val defBranch = getString(R.string.def_branch) + repo.defaultBranch
        val forks = getString(R.string.forks) + repo.forks
        val watchers = getString(R.string.watchers) + repo.watchers
        val visibility = getString(R.string.visibility) + repo.visibility
        viewBinding?.let {
            it.nameTextView.text = repo.name
            it.defaultBranchTextView.text = defBranch
            it.forksTextView.text = forks
            it.visibilityTextView.text = visibility
            it.watchersTextView.text = watchers
        }

    }


}