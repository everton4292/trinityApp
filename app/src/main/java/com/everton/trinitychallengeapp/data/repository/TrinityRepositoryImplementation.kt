package com.everton.trinitychallengeapp.data.repository

import com.everton.trinitychallengeapp.data.local.TrinityDao
import com.everton.trinitychallengeapp.data.model.User

class TrinityRepositoryImplementation(private val trinityDao: TrinityDao) : TrinityRepository {
    override suspend fun saveUser(user: User): Long {
        return trinityDao.saveAccessToken(user)
    }

    override suspend fun deleteUser(user: User) {
        return trinityDao.deleteAccessToken(user)
    }

    override suspend fun getUser(): MutableList<User> {
        return trinityDao.getUser()
    }
}