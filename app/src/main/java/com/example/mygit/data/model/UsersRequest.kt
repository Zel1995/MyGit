package com.example.mygit.data.model

import com.google.gson.annotations.SerializedName

data class UsersRequest(
    @SerializedName("id")
    val id: Long,
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("repos_url")
    val reposUrl:String
)

