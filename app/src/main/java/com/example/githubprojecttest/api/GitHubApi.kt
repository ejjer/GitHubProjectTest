package com.example.githubprojecttest.api

import com.example.githubprojecttest.model.GitHubUserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{user}/followers")
    fun loadFollowers(@Path("user") user: String): Call<List<GitHubUserModel>>
}