package com.everton.trinitychallengeapp.data.repository

import com.everton.trinitychallengeapp.data.source.local.TrinityDao
import com.everton.trinitychallengeapp.data.model.Data
import com.everton.trinitychallengeapp.data.source.remote.NasaService
import com.everton.trinitychallengeapp.domain.repository.TrinityRepository

class TrinityRepositoryImplementation(
    private val trinityDao: TrinityDao,
    private val nasaService: NasaService
) : TrinityRepository {
    override suspend fun saveLocalData(localData: Data): Long {
        return trinityDao.saveLocalPhoto(localData)
    }

    override suspend fun getLocalData(): Data {
        return trinityDao.getLocalPhoto()
    }

    override suspend fun getMarsData(): Data {
        return nasaService.getMarsData()
    }
}