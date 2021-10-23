package com.example.mygit.ui.details

import android.os.Bundle
import android.view.View
import com.example.mygit.App
import com.example.mygit.R
import com.example.mygit.data.bus.EventBus
import com.example.mygit.databinding.FragmentUserBinding
import com.example.mygit.domain.model.GitUser
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
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

    private val compositeDisposable = CompositeDisposable()
    private var viewBinding: FragmentUserBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentUserBinding.bind(view)
        initViews()
    }


    private fun initViews() {
        val user = arguments?.getParcelable<GitUser>(USER_KEY) ?: return
        viewBinding?.userAge?.text = user.age.toString()
        viewBinding?.userName?.text = user.name
        initLike(user)

        viewBinding?.likeImageView?.setOnClickListener{
            val bus = (requireActivity().application as App).counterBus
            bus.post(EventBus.Like(user.id))
            compositeDisposable += bus.get().subscribe {
                if (it.id == user.id) {
                    user.like = user.like.not()
                    initLike(user)
                }
            }
        }
    }

    private fun initLike(user: GitUser) {
        if (user.like) {
            viewBinding?.likeImageView?.setImageResource(R.drawable.ic_like)
        } else {
            viewBinding?.likeImageView?.setImageResource(R.drawable.ic_like_not)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}