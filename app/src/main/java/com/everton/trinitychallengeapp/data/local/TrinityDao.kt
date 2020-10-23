package com.everton.trinitychallengeapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.everton.trinitychallengeapp.data.model.User


@Dao
interface TrinityDao {
    @Insert
    suspend fun saveAccessToken(user: User): Long

    @Delete
    suspend fun deleteAccessToken(user: User)

    @Query("SELECT * FROM user_table")
    suspend fun getUser(): MutableList<User>

}