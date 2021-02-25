package com.alextsy.githubusersapp.api

import com.alextsy.githubusersapp.BuildConfig
import com.alextsy.githubusersapp.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val CLIENT_ID = BuildConfig.GITHUB_ACCESS_TOKEN
    }

    @Headers("Accept: application/vnd.github.v3+json", "Authorization: token $CLIENT_ID")
    @GET("users")
    suspend fun getUsers(
        @Query("since") userId: Int,
        @Query("per_page") perPage: Int = 20
    ): List<User>

    @Headers("Accept: application/vnd.github.v3+json", "Authorization: token $CLIENT_ID")
    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): User
}