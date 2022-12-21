package com.example.githubprojecttest.model.dataBase

import androidx.room.*

@Dao
interface RepositoryDao {
    @Query("SELECT * FROM RepositoryEntity")
    fun all(): List<RepositoryEntity>

    @Query("SELECT * FROM RepositoryEntity WHERE repositoryName LIKE:repositoryName")
    fun getData(repositoryName: String): List<RepositoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: RepositoryEntity)

    @Update
    fun update(entity: RepositoryEntity)

    @Delete
    fun delete(entity: RepositoryEntity)
    @Query("DELETE FROM RepositoryEntity WHERE repositoryName=:repositoryName")
    fun deleteByRepositoryName(repositoryName:String)
}