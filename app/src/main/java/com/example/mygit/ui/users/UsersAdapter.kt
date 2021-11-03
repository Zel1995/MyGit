package com.example.mygit.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygit.R
import com.example.mygit.domain.model.GitUser

class UsersAdapter(
    private val itemClicked: (user: GitUser) -> Unit,
) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersAdapter.UsersViewHolder {
        return UsersViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.user_item, parent, false
            )
        )
    }

    private var users = listOf<GitUser>()

    fun setUsers(users: List<GitUser>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: UsersAdapter.UsersViewHolder, position: Int) {
        val item = users[position]
        with(holder) {
            login.text = item.login
            Glide.with(avatar)
                .load(item.avatarUrl)
                .into(avatar)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val login: TextView = itemView.findViewById(R.id.login_text_view)
        val avatar: ImageView = itemView.findViewById(R.id.avatar_image_view)

        init {
            itemView.setOnClickListener { itemClicked(users[adapterPosition]) }
        }
    }
}