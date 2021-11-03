package com.example.mygit.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitUser(
    val id: Long,
    val login: String,
    val avatarUrl: String,
    val url: String,
    val reposUrl:String,
) : Parcelable