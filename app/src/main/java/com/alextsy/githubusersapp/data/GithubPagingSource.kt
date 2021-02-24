package com.alextsy.githubusersapp.data

import androidx.paging.PagingSource
import com.alextsy.githubusersapp.api.GithubApi
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 1

class GithubPagingSource(
    private val githubApi: GithubApi
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX

        return try {
            val users = githubApi.getUsers(params.loadSize)

            LoadResult.Page(
                data = users,
                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (users.isEmpty()) null else position + 1
            )
        } catch (exception: IOException){
            LoadResult.Error(exception)
        } catch (exception: HttpException){
            LoadResult.Error(exception)
        }
    }
}