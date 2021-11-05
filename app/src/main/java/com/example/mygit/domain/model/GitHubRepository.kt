package com.example.mygit.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubRepository(
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String?,
    val visibility:String,
    val forks:Int,
    val watchers:Int,
    val defaultBranch:String
):Parcelable