package com.example.githubprojecttest.repos

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubprojecttest.adapters.ListRepoAdapter
import com.example.githubprojecttest.adapters.ListUsersAdapter
import com.example.githubprojecttest.api.GitHubApi
import com.example.githubprojecttest.api.GitHubApiService
import com.example.githubprojecttest.model.GitHubUserModel
import com.example.githubprojecttest.model.GitHubUserRepoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubRepository(private val gitHubApi: GitHubApi) {
    fun loadUsers(userName: String): LiveData<List<GitHubUserModel>?> {
        val data = MutableLiveData<List<GitHubUserModel>?>()
        gitHubApi.loadFollowers(userName).enqueue(object : Callback<List<GitHubUserModel>> {
            override fun onResponse(
                call: Call<List<GitHubUserModel>>,
                response: Response<List<GitHubUserModel>>
            ) {
                val list = response.body()
                data.postValue(list)
            }

            override fun onFailure(call: Call<List<GitHubUserModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return data
    }

    fun loadRepos(userName: String): LiveData<List<GitHubUserRepoModel>?> {
        val data = MutableLiveData<List<GitHubUserRepoModel>?>()
        gitHubApi.loadRepos(userName).enqueue(object : Callback<List<GitHubUserRepoModel>> {
            override fun onResponse(
                call: Call<List<GitHubUserRepoModel>>,
                response: Response<List<GitHubUserRepoModel>>
            ) {
                val list = response.body()
                data.postValue(list)
            }

            override fun onFailure(call: Call<List<GitHubUserRepoModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return data
    }
}