package com.example.githubprojecttest.model.dataBase

import androidx.room.RoomDatabase

@androidx.room.Database(
    entities = [
        RepositoryEntity::class

    ],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}