package com.example.githubprojecttest.navigation

import com.example.githubprojecttest.model.GitHubUserModel

interface Navigation {
    fun showGitHubListFragment()
    fun showCurrentGitHubUser()
    fun showSettings()
    fun showRepos()
    fun showCurrentUser(user: GitHubUserModel)
}