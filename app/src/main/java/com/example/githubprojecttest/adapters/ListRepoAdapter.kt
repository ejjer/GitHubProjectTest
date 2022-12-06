package com.example.githubprojecttest.adapters

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubprojecttest.R
import com.example.githubprojecttest.model.GitHubUserRepoModel

class ListRepoAdapter(private val List<GitHubUserRepoModel>: List<GitHubUserRepoModel>?) :
    RecyclerView.Adapter<ListRepoAdapter.ViewHolder>(), Parcelable {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repositoryName = itemView.findViewById<TextView>(R.id.reposName)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRepoAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_git_hub_users_repo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListRepoAdapter.ViewHolder, position: Int) {
        val repository = gitHubRepository[position]
        holder.repositoryName.text = repository.fullName
    }

    override fun getItemCount(): Int {
        return gitHubRepository.size
    }
}
