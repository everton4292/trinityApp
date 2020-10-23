package com.everton.trinitychallengeapp.data.repository

import com.everton.trinitychallengeapp.data.model.Data
import com.everton.trinitychallengeapp.data.model.User

interface TrinityRepository {
    suspend fun saveUser(user: User): Long
    suspend fun deleteUser(user: User)
    suspend fun getUser(): MutableList<User>

    //remote
    suspend fun getMarsData(): Data

}