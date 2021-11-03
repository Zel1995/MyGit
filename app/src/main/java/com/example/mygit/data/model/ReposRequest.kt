package com.example.mygit.data.model

import com.google.gson.annotations.SerializedName

data class ReposRequest(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName:String,
    @SerializedName("description")
    val description:String,
    @SerializedName("visibility")
    val visibility:String,
    @SerializedName("forks")
    val forks:Int,
    @SerializedName("watchers")
    val watchers:Int,
    @SerializedName("default_branch")
    val defaultBranch:String,
    @SerializedName("owner")
    val owner:OwnerRequest
)
