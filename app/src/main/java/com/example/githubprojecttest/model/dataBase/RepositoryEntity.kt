package com.example.githubprojecttest.model.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class RepositoryEntity (
    @PrimaryKey(autoGenerate = true) val id:Long,
    val repositoryName: String,
    val repositoryLanguage:String
        )