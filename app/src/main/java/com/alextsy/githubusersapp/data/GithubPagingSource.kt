package com.alextsy.githubusersapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alextsy.githubusersapp.api.GithubApi
import retrofit2.HttpException
import java.io.IOException
import java.util.ArrayList

private const val GITHUB_STARTING_ID_INDEX = 0

class GithubPagingSource(
    private val githubApi: GithubApi
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position = params.key ?: GITHUB_STARTING_ID_INDEX

        return try {
            val users = githubApi.getUsers(position, params.loadSize)

            LoadResult.Page(
                data = users,
                prevKey = if (position == GITHUB_STARTING_ID_INDEX) null else position - 30,
                nextKey = if (users.isEmpty()) null else users.last().id
            )
        } catch (exception: IOException){
            LoadResult.Error(exception)
        } catch (exception: HttpException){
            LoadResult.Error(exception)
        }
    }
}