package com.alextsy.githubusersapp.ui.details

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.alextsy.githubusersapp.data.GithubRepository
import com.alextsy.githubusersapp.data.User
import kotlinx.coroutines.launch

class DetailsViewModel @ViewModelInject constructor(
    private val repository: GithubRepository,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {

    private val _response = MutableLiveData<User>()

    val userLD: LiveData<User>
        get() = _response

    val user = state.get<User>("user")

    var username = user?.login ?: ""
        set(value) {
            field = value
            state.set("username", value)
        }

    init {
        getUserDetailsFromGithub(username)
    }

    private fun getUserDetailsFromGithub(username: String) {
        viewModelScope.launch {
            try {
                val result = repository.getUserInfo(username)
                _response.value = result
            } catch (e: Exception) {
            }
        }
    }
}