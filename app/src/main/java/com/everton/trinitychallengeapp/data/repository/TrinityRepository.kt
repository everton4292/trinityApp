package com.everton.trinitychallengeapp.data.repository

import com.everton.trinitychallengeapp.data.model.Data


interface TrinityRepository {
    //local
    suspend fun saveLocalData(localData: Data): Long
    suspend fun getLocalPhoto(): Data

    //remote
    suspend fun getMarsData(): Data

}