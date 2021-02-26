package com.alextsy.githubusersapp.ui.details

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.alextsy.githubusersapp.R
import com.alextsy.githubusersapp.databinding.FragmentDetailsBinding
import com.alextsy.githubusersapp.ui.users.UsersViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailsBinding.bind(view)

        viewModel.userLD.observe(viewLifecycleOwner) {
            binding.apply {
                textViewLocation.text = it.location
                textViewDetailsName.text = it.name
            }
        }

        binding.apply {
            val user = args.user

            Glide.with(this@DetailsFragment)
                .load(user.avatar_url)
                .error(R.drawable.ic_default_avatar)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        textViewDetailsName.isVisible = true
                        textViewLocation.isVisible = true
                        textViewDetailsUrl.isVisible = true
                        return false
                    }
                })
                .into(imageViewDetailsAvatar)

            val uri = Uri.parse(user.html_url)
            val intent = Intent(Intent.ACTION_VIEW, uri)

            textViewDetailsUrl.apply {
                text = user.html_url
                setOnClickListener {
                    context.startActivity(intent)
                }
                paint.isUnderlineText = true
            }
        }
    }
}