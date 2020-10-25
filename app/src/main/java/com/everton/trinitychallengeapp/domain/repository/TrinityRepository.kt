package com.everton.trinitychallengeapp.domain.repository

import com.everton.trinitychallengeapp.data.model.Data


interface TrinityRepository {
    //local functions to access room database
    suspend fun saveLocalData(localData: Data): Long
    suspend fun getLocalData(): Data

    //remote functions to fetch data from API using retrofit
    suspend fun getMarsData(): Data

}