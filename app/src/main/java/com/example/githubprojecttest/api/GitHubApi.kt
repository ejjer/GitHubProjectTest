package com.example.githubprojecttest.api

import com.example.githubprojecttest.model.GitHubUserModel
import com.example.githubprojecttest.model.GitHubUserRepoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{user}/followers")
    fun loadFollowers(@Path("user") user: String): Call<List<GitHubUserModel>>

    @GET("users/{username}/repos")
    fun loadRepos(@Path("username") username: String): Call<List<GitHubUserRepoModel>>
}