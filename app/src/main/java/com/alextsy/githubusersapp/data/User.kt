package com.alextsy.githubusersapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val node_id: String,
    val avatar_url: String,
    val login: String,
    val name: String,
    val html_url: String,
    val location: String?
) : Parcelable { }