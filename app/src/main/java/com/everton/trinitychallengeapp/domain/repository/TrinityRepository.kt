package com.everton.trinitychallengeapp.domain.repository

import com.everton.trinitychallengeapp.data.model.Data


interface TrinityRepository {
    //local
    suspend fun saveLocalData(localData: Data): Long
    suspend fun getLocalData(): Data

    //remote
    suspend fun getMarsData(): Data

}