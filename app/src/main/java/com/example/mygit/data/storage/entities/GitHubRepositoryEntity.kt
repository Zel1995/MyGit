package com.example.mygit.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GitHubRepositoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "fullName")
    val fullName: String,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "visibility")
    val visibility: String,
    @ColumnInfo(name = "forks")
    val forks: Int,
    @ColumnInfo(name = "watchers")
    val watchers: Int,
    @ColumnInfo(name = "default_branch")
    val defaultBranch: String,
    @ColumnInfo(name = "owner_login")
    val ownerLogin: String
)