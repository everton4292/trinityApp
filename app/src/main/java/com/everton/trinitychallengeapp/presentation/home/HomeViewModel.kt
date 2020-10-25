package com.everton.trinitychallengeapp.presentation.home

import androidx.lifecycle.MutableLiveData
import com.everton.trinitychallengeapp.data.model.Data
import com.everton.trinitychallengeapp.data.model.Photo
import com.everton.trinitychallengeapp.domain.repository.TrinityRepository
import com.everton.trinitychallengeapp.presentation.BaseViewModel
import kotlinx.coroutines.launch


class HomeViewModel(private val trinityRepository: TrinityRepository) : BaseViewModel() {

    private var listPhotosHelper: MutableList<Photo> = mutableListOf()
    var listPhotos: MutableLiveData<MutableList<Photo>> = MutableLiveData()
    var errorRoom = MutableLiveData<Unit>()

    fun getMarsData() {
        scope.launch {
            try {
                val data = trinityRepository.getMarsData()
                listPhotosHelper.addAll(data.photos)
                listPhotos.value = listPhotosHelper
                saveDataLocal(data)
            } catch (e: Exception) {
                e.printStackTrace()
                getLocalData()
            }
        }
    }

    private fun saveDataLocal(localData: Data) {
        scope.launch {
            try {
                trinityRepository.saveLocalData(localData)
            } catch (e: Exception) {
                errorRoom.value = Unit
                e.printStackTrace()
            }
        }
    }

    private fun getLocalData() {
        scope.launch {
            try {
                val data: Data = trinityRepository.getLocalData()
                listPhotosHelper.addAll(data.photos)
                listPhotos.value = listPhotosHelper
            } catch (e: Exception) {
                errorRoom.value = Unit
                e.printStackTrace()
            }
        }
    }
}