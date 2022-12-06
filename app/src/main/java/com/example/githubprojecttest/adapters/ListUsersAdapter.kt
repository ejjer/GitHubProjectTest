package com.example.githubprojecttest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubprojecttest.R
import com.example.githubprojecttest.model.GitHubUserModel

class ListUsersAdapter(private val users: List<GitHubUserModel>, val listener: ClickListener) :
    RecyclerView.Adapter<ListUsersAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userAvatar = itemView.findViewById<ImageView>(R.id.userAvatar)
        val userName = itemView.findViewById<TextView>(R.id.userName)
        val userId = itemView.findViewById<TextView>(R.id.userId)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUsersAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_git_hub_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListUsersAdapter.ViewHolder, position: Int) {
        val user = users[position]
        holder.userName.text = user.login
        holder.userId.text = user.id.toString()
        Glide.with(holder.itemView.context).load(user.avatarUrl).into(holder.userAvatar)
        holder.itemView.setOnClickListener{
            listener.onItemClick(user)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

}

interface ClickListener {
    fun onItemClick(user: GitHubUserModel)
}