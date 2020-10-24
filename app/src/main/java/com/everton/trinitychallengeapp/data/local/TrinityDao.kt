package com.everton.trinitychallengeapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.everton.trinitychallengeapp.data.model.Data



@Dao
interface TrinityDao {
    @Insert
    suspend fun saveLocalPhoto(localData: Data): Long

    @Query("SELECT * FROM data_table")
    suspend fun getLocalPhoto(): Data

}