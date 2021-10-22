package com.example.mygit.ui.details

import android.os.Bundle
import android.view.View
import com.example.mygit.R
import com.example.mygit.databinding.FragmentUserBinding
import com.example.mygit.domain.model.GitUser
import moxy.MvpAppCompatFragment

class UserFragment : MvpAppCompatFragment(R.layout.fragment_user) {
    companion object {
        private const val USER_KEY = "USER_KEY"
        fun newInstance(user: GitUser) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER_KEY, user)
                }
            }
    }
    private var viewBinding: FragmentUserBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentUserBinding.bind(view)
        val user = arguments?.getParcelable<GitUser>(USER_KEY)
        viewBinding?.userAge?.text = user?.age.toString()
        viewBinding?.userName?.text = user?.name

    }

}