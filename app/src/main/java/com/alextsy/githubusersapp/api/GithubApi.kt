package com.alextsy.githubusersapp.api

import com.alextsy.githubusersapp.data.User
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubApi {

    companion object{
        const val BASE_URL = "https://api.github.com/"
    }

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("users")
    suspend fun getUsers(
        @Query("per_page") perPage:Int
    ) : List<User>
}