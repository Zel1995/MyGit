package com.example.mygit.ui.details

import android.os.Bundle
import com.example.mygit.domain.model.GitUser
import moxy.MvpAppCompatFragment

class UserFragment : MvpAppCompatFragment() {
    companion object {
        private const val USER_KEY = "USER_KEY"
        fun newInstance(user: GitUser) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER_KEY,user)
                }
        }
    }

}