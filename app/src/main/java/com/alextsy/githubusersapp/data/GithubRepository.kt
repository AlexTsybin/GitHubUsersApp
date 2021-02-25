package com.alextsy.githubusersapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.alextsy.githubusersapp.api.GithubApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(private val githubApi: GithubApi) {

    fun getUsersResults() =
        Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GithubPagingSource(githubApi) }
        ).liveData

    suspend fun getUserInfo(username: String): User =
        githubApi.getUser(username)
}