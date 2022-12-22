package com.example.githubprojecttest.navigation

import com.example.githubprojecttest.adapters.ClickListener
import com.example.githubprojecttest.model.GitHubUserModel

interface Navigation : ClickListener {
    fun showGitHubListFragment()
    fun showInputNameGitHubUserFragment()
    fun showCurrentGitHubUser()
    fun showSettings()
    fun showRepos()
    fun showCurrentUser(user: GitHubUserModel)
}