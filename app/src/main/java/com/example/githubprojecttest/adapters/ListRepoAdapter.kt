package com.example.githubprojecttest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubprojecttest.R
import com.example.githubprojecttest.model.GitHubUserRepoModel


class ListRepoAdapter(private val gitHubRepository: List<GitHubUserRepoModel>) :
    RecyclerView.Adapter<ListRepoAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repositoryName: TextView = itemView.findViewById(R.id.reposName)
        val repositoryDataOfCreation: TextView = itemView.findViewById(R.id.reposDateOfCreation)
        val repositoryLanguage: TextView = itemView.findViewById(R.id.reposLanguage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRepoAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_git_hub_users_repo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListRepoAdapter.ViewHolder, position: Int) {
        val repository = gitHubRepository[position]
        holder.repositoryName.text = repository.name
        holder.repositoryDataOfCreation.text = repository.createdAt
        holder.repositoryLanguage.text = repository.language as CharSequence?
    }

    override fun getItemCount(): Int {
        return gitHubRepository.size
    }
}