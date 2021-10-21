package com.example.mygit.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mygit.R
import com.example.mygit.domain.model.GitUser

class UsersAdapter(
    private val itemClicked: (user: GitUser) -> Unit
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

    fun setUsers(users:List<GitUser>){
        this.users = users
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: UsersAdapter.UsersViewHolder, position: Int) {
        val item = users[position]
        with(holder) {
            name.text= item.name
            age.text = item.age.toString()
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_name)
        val age: TextView = itemView.findViewById(R.id.tv_age)

        init {
            itemView.setOnClickListener { itemClicked(users[adapterPosition]) }
        }
    }
}