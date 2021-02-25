package com.alextsy.githubusersapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alextsy.githubusersapp.databinding.UsersLoadProgressBinding

class UsersLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<UsersLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            UsersLoadProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(private val binding: UsersLoadProgressBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.textViewRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                textViewRetry.isVisible = loadState !is LoadState.Loading
                textViewError.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}