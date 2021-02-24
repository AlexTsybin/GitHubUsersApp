package com.alextsy.githubusersapp.ui.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alextsy.githubusersapp.R
import com.alextsy.githubusersapp.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment(R.layout.fragment_users) {

    private val viewModel by viewModels<UsersViewModel>()

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentUsersBinding.bind(view)

        val adapter = UsersAdapter()

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }

        viewModel.users.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}