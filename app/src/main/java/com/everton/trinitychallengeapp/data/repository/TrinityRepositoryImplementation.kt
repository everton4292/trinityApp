package com.everton.trinitychallengeapp.data.repository

import com.everton.trinitychallengeapp.data.local.TrinityDao
import com.everton.trinitychallengeapp.data.model.Data
import com.everton.trinitychallengeapp.data.model.User
import com.everton.trinitychallengeapp.data.remote.NasaService

class TrinityRepositoryImplementation(
    private val trinityDao: TrinityDao,
    private val nasaService: NasaService
) : TrinityRepository {
    override suspend fun saveUser(user: User): Long {
        return trinityDao.saveAccessToken(user)
    }

    override suspend fun deleteUser(user: User) {
        return trinityDao.deleteAccessToken(user)
    }

    override suspend fun getUser(): MutableList<User> {
        return trinityDao.getUser()
    }

    override suspend fun getMarsData(): Data {
        return nasaService.getMarsData()
    }


}