package com.example.githubprojecttest.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GitHubApiService {
    private const val BASE_URL = "https://api.github.com/"
    fun retrofitApi(): GitHubApi {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(GitHubApi::class.java)
    }
}

