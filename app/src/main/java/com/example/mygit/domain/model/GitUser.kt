package com.example.mygit.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitUser(
    val id:String,
    val name:String,
    val age:Int
):Parcelable