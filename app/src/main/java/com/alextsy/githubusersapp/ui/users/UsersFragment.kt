package com.alextsy.githubusersapp.ui.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alextsy.githubusersapp.R
import com.alextsy.githubusersapp.data.User
import com.alextsy.githubusersapp.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment(R.layout.fragment_users), UsersAdapter.OnItemClickListener {

    private val viewModel by viewModels<UsersViewModel>()

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentUsersBinding.bind(view)

        val adapter = UsersAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = UsersLoadStateAdapter { adapter.retry() },
                footer = UsersLoadStateAdapter { adapter.retry() }
            )
        }

        viewModel.users.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onItemClick(user: User) {
        val action = UsersFragmentDirections.actionUsersFragmentToDetailsFragment(user)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}