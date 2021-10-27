package com.example.mygit.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mygit.R
import com.example.mygit.domain.model.GitHubRepository

class ReposAdapter(val itemClicked: (GitHubRepository) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<GitHubRepository>()

    fun setData(dataToSet: List<GitHubRepository>) {
        data.apply {
            clear()
            addAll(dataToSet)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        (holder as UserViewHolder).let { userViewHolder ->
            userViewHolder.repositoryName.text = item.name
            userViewHolder.repositoryDescription.text = item.description
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repositoryName: TextView = itemView.findViewById(R.id.repository_name_text_view)
        val repositoryDescription: TextView =
            itemView.findViewById(R.id.repository_description_text_view)

        init {
            itemView.setOnClickListener { itemClicked.invoke(data[adapterPosition]) }
        }
    }
}