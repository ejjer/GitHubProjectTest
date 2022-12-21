package com.example.githubprojecttest.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubprojecttest.api.GitHubApiService
import com.example.githubprojecttest.model.GitHubUserModel
import com.example.githubprojecttest.model.GitHubUserRepoModel
import com.example.githubprojecttest.repos.GitHubRepository

open class MainActivityViewModel : ViewModel() {
    val repository = GitHubRepository(GitHubApiService.retrofitApi())
    var inputUserName = MutableLiveData<String>()
    val theme = MutableLiveData<String>()

    var loadRepos: LiveData<List<GitHubUserRepoModel>>? = null
    fun loadRepo(userName: String) = repository.loadRepos(userName)

    var loadFollowers: LiveData<List<GitHubUserModel>>? = null
    fun loadFollowers(userName:String) = repository.loadUsers(userName)

}
