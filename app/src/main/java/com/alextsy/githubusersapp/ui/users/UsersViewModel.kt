package com.alextsy.githubusersapp.ui.users

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alextsy.githubusersapp.data.GithubRepository

class UsersViewModel @ViewModelInject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    val users = repository.getUsersResults().cachedIn(viewModelScope)
}