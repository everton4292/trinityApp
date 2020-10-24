package com.everton.trinitychallengeapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.everton.trinitychallengeapp.data.model.Data
import com.everton.trinitychallengeapp.data.model.Photo
import com.everton.trinitychallengeapp.domain.repository.TrinityRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class HomeViewModel(private val trinityRepository: TrinityRepository) : ViewModel() {

    private var listPhotosHelper: MutableList<Photo> = mutableListOf()
    var listPhotos: MutableLiveData<MutableList<Photo>> = MutableLiveData()

    val scope = CoroutineScope(
        Job() + Dispatchers.Main
    )

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
                e.printStackTrace()
            }
        }
    }
}