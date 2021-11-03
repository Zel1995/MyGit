package com.example.mygit.data.model

import com.google.gson.annotations.SerializedName

data class OwnerRequest(
    @SerializedName("login")val login:String)